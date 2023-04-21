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

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());
    private final HeroAircraft heroAircraft = HeroAircraft.getInstance();
    private final List<AbstractEnemy> enemyAircraftObjects = new LinkedList<>();
    private final List<AbstractBullet> heroBullets = new LinkedList<>();
    private final List<AbstractBullet> enemyBullets = new LinkedList<>();
    private final List<AbstractProp> enemyProps = new LinkedList<>();
    private final List<GameOverCallback> callbacks = new LinkedList<>();
    /**
     * BOSS 机
     */
    private AbstractEnemy boss = null;

    /**
     * 当前得分
     */
    private int score = 0;
    private Difficulty difficulty;
    private AbstractGenerateStrategy generateStrategy = new EasyGenerateStrategy();
    private final AbstractPaintStrategy paintStrategy = new SimplePaintStrategy();
    private AbstractMusicStrategy musicStrategy = new BasicMusicStrategy();

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

    public int getScore() {
        return score;
    }

    public Difficulty getDifficulty() {
        return difficulty;
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
    public void action() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            // 周期性执行（控制频率）
            if (generateStrategy.isTimeToGenerate()) {
                // BOSS 机产生
                var newBoss = generateStrategy.generateBoss(this.boss, score);
                if (newBoss != this.boss) {
                    this.boss = newBoss;
                    this.enemyAircraftObjects.add(this.boss);
                    musicStrategy.setBgm(AbstractMusicStrategy.BgmType.BOSS);
                }
                // 新敌机产生
                enemyAircraftObjects.addAll(generateStrategy.generateEnemy(enemyAircraftObjects.size()));
                // 飞机射出子弹
                shootAction();
            }
            // 飞行物移动
            moveAction();
            // 撞击检测
            crashCheckAction();
            // 清理已损毁的飞行物
            cleanInvalid();
            //每个时刻重绘界面
            repaint();
            // 检查BOSS机是否存活
            if (boss != null && boss.notValid()) {
                boss = null;
                musicStrategy.setBgm(AbstractMusicStrategy.BgmType.NORMAL);
            }
            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                gameOver();
            }

        };

        // 以固定延迟时间进行执行
        executorService.scheduleWithFixedDelay(task, Config.REFRESH_INTERVAL, Config.REFRESH_INTERVAL, TimeUnit.MILLISECONDS);

        // 启动 BGM
        musicStrategy.setBgm(AbstractMusicStrategy.BgmType.NORMAL);
    }

    private void moveAction() {
        heroBullets.forEach(AbstractBullet::forward);
        enemyBullets.forEach(AbstractBullet::forward);
        enemyAircraftObjects.forEach(AbstractEnemy::forward);
        enemyProps.forEach(AbstractProp::forward);
    }

    private void shootAction() {
        enemyAircraftObjects.forEach(enemy -> enemyBullets.addAll(enemy.shoot()));
        heroBullets.addAll(heroAircraft.shoot());
        musicStrategy.playBullet();
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
        executorService.shutdown();
        musicStrategy.setBgm(AbstractMusicStrategy.BgmType.NONE);
        musicStrategy.playGameOver();
        for (var callback : callbacks) {
            callback.action(this);
        }
    }

    /**
     * 删除无效的飞行物
     */
    private void cleanInvalid() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircraftObjects.removeIf(AbstractFlyingObject::notValid);
        enemyProps.removeIf(AbstractFlyingObject::notValid);
    }

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintStrategy.draw(g, enemyBullets, enemyProps, heroBullets, enemyAircraftObjects, heroAircraft, score);
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
