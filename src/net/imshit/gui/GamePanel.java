package net.imshit.gui;

import net.imshit.Config;
import net.imshit.element.aircraft.enemy.AbstractEnemy;
import net.imshit.element.aircraft.hero.HeroAircraft;
import net.imshit.element.basic.AbstractFlyingObject;
import net.imshit.element.bullet.AbstractBullet;
import net.imshit.element.prop.AbstractProp;
import net.imshit.io.control.HeroController;
import net.imshit.io.resource.ImageManager;
import net.imshit.logic.config.Difficulty;
import net.imshit.logic.game.generate.AbstractGenerateStrategy;
import net.imshit.logic.game.generate.EasyGenerateStrategy;
import net.imshit.logic.game.music.AbstractMusicStrategy;
import net.imshit.logic.game.music.BasicMusicStrategy;
import net.imshit.logic.game.music.MuteMusicStrategy;
import net.imshit.logic.game.paint.AbstractPaintStrategy;
import net.imshit.logic.game.paint.SimplePaintStrategy;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 游戏主面板，游戏启动
 *
 * @author jim-shop
 */
public class GamePanel extends JPanel {

    private ScheduledExecutorService executorService;

    private final HeroAircraft heroAircraft = HeroAircraft.getInstance();
    private final List<AbstractEnemy> enemyAircraftObjects = new LinkedList<>();
    private final List<AbstractBullet> heroBullets = new LinkedList<>();
    private final List<AbstractBullet> enemyBullets = new LinkedList<>();
    private final List<AbstractProp> enemyProps = new LinkedList<>();
    private final List<GameOverCallback> callbacks = new LinkedList<>();
    private AbstractEnemy boss;

    private AbstractGenerateStrategy generateStrategy = new EasyGenerateStrategy();
    private final AbstractPaintStrategy paintStrategy = new SimplePaintStrategy();
    private AbstractMusicStrategy musicStrategy = new BasicMusicStrategy();

    private int score;
    private Difficulty difficulty;

    public int getScore() {
        return score;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public GamePanel() {
        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);
    }

    public void setMusicOn(boolean musicOn) {
        if (musicOn) {
            this.musicStrategy = new BasicMusicStrategy();
        } else {
            this.musicStrategy = new MuteMusicStrategy();
        }
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        switch (difficulty) {
            case EASY -> {
                generateStrategy = new EasyGenerateStrategy();
                paintStrategy.setBackgroundImage(ImageManager.BACKGROUND_IMAGE_EASY);
            }
            case MEDIUM -> {
                paintStrategy.setBackgroundImage(ImageManager.BACKGROUND_IMAGE_MEDIUM);
            }
            case HARD -> {
                paintStrategy.setBackgroundImage(ImageManager.BACKGROUND_IMAGE_HARD);
            }
            default -> {
                generateStrategy = new EasyGenerateStrategy();
                paintStrategy.setBackgroundImage(ImageManager.BACKGROUND_IMAGE_EASY);
            }
        }
    }

    public void addGameOverCallback(GameOverCallback callback) {
        callbacks.add(callback);
    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action(SettingPanel settingPanel) {

        this.setDifficulty(settingPanel.getDifficulty());
        this.setMusicOn(settingPanel.getMusicOn());
        this.musicStrategy.setBgm(AbstractMusicStrategy.BgmType.NORMAL);

        this.executorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());
        this.heroAircraft.reset();
        this.enemyAircraftObjects.clear();
        this.heroBullets.clear();
        this.enemyBullets.clear();
        this.enemyProps.clear();
        this.boss = null;
        this.score = 0;

        this.executorService.scheduleWithFixedDelay(this::update, Config.REFRESH_INTERVAL, Config.REFRESH_INTERVAL, TimeUnit.MILLISECONDS);
    }

    private void update() {
        // 周期性执行（控制频率）
        if (this.generateStrategy.isTimeToGenerate()) {
            // BOSS 机产生
            var newBoss = this.generateStrategy.generateBoss(this.boss, this.score);
            if (newBoss != this.boss) {
                this.boss = newBoss;
                this.enemyAircraftObjects.add(this.boss);
                this.musicStrategy.setBgm(AbstractMusicStrategy.BgmType.BOSS);
            }
            // 新敌机产生
            this.enemyAircraftObjects.addAll(this.generateStrategy.generateEnemy(enemyAircraftObjects.size()));
            // 飞机射出子弹
            this.shootAction();
        }
        // 飞行物移动
        this.moveAction();
        // 撞击检测
        this.crashCheckAction();
        // 清理已损毁的飞行物
        this.cleanInvalid();
        //每个时刻重绘界面
        this.repaint();
        // 游戏结束检查英雄机是否存活
        if (this.heroAircraft.getHp() <= 0) {
            this.gameOver();
        }
    }

    private void moveAction() {
        this.heroBullets.forEach(AbstractBullet::forward);
        this.enemyBullets.forEach(AbstractBullet::forward);
        this.enemyAircraftObjects.forEach(AbstractEnemy::forward);
        this.enemyProps.forEach(AbstractProp::forward);
    }

    private void shootAction() {
        this.enemyAircraftObjects.forEach(enemy -> this.enemyBullets.addAll(enemy.shoot()));
        this.heroBullets.addAll(heroAircraft.shoot());
        this.musicStrategy.playBullet();
    }

    //***********************
    //      Action 各部分
    //***********************

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // 敌机子弹攻击英雄
        enemyBullets.stream().filter(heroAircraft::crash).forEach(bullet -> {
            heroAircraft.decreaseHp(bullet.getPower());
            bullet.vanish();
            musicStrategy.playBulletHit();
        });

        // 英雄子弹攻击敌机
        heroBullets.stream().filter(AbstractBullet::valid).forEach(bullet -> {
            enemyAircraftObjects.stream().filter(AbstractEnemy::valid).filter(bullet::crash).forEach(enemyAircraft -> {
                enemyAircraft.decreaseHp(bullet.getPower());
                if (enemyAircraft.notValid()) {
                    enemyProps.addAll(enemyAircraft.prop());
                    score += 30;
                }
                bullet.vanish();
                musicStrategy.playBulletHit();
            });
        });

        // 英雄机 与 敌机 相撞，均损毁
        enemyAircraftObjects.stream().filter(AbstractEnemy::valid).filter(heroAircraft::crash).forEach(enemyAircraft -> {
            enemyAircraft.vanish();
            heroAircraft.decreaseHp(Integer.MAX_VALUE);
        });

        // 我方获得道具，道具生效
        enemyProps.stream().filter(heroAircraft::crash).forEach(prop -> {
            prop.use(heroAircraft);
            prop.vanish();
            musicStrategy.playGetSupply();
        });
    }

    private void gameOver() {
        // 游戏结束
        this.executorService.shutdown();
        this.musicStrategy.setBgm(AbstractMusicStrategy.BgmType.NONE);
        this.musicStrategy.playGameOver();
        for (var callback : this.callbacks) {
            callback.action(this);
        }
    }

    /**
     * 删除无效的飞行物
     */
    private void cleanInvalid() {
        this.enemyBullets.removeIf(AbstractFlyingObject::notValid);
        this.heroBullets.removeIf(AbstractFlyingObject::notValid);
        this.enemyAircraftObjects.removeIf(AbstractFlyingObject::notValid);
        this.enemyProps.removeIf(AbstractFlyingObject::notValid);
        if (this.boss != null && this.boss.notValid()) {
            this.boss = null;
            this.musicStrategy.setBgm(AbstractMusicStrategy.BgmType.NORMAL);
        }
    }

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.paintStrategy.draw(g, this.enemyBullets, this.enemyProps, this.heroBullets, this.enemyAircraftObjects, this.heroAircraft, this.score);
    }

    public interface GameOverCallback {
        /**
         * 当游戏结束后，执行回调函数
         *
         * @param host Game对象本身
         */
        void action(GamePanel host);
    }
}
