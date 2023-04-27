package net.imshit.gui;

import net.imshit.Config;
import net.imshit.element.AbstractFlyingObject;
import net.imshit.element.aircraft.enemy.AbstractEnemy;
import net.imshit.element.aircraft.hero.HeroAircraft;
import net.imshit.element.animation.DyingAnimation;
import net.imshit.element.bullet.AbstractBullet;
import net.imshit.element.bullet.EnemyBullet;
import net.imshit.element.bullet.HeroBullet;
import net.imshit.element.prop.AbstractProp;
import net.imshit.io.resource.ImageManager;
import net.imshit.logic.callback.Callback;
import net.imshit.logic.config.Difficulty;
import net.imshit.logic.control.HeroController;
import net.imshit.logic.listener.EnemyListener;
import net.imshit.logic.listener.Event;
import net.imshit.logic.music.AbstractMusicStrategy;
import net.imshit.logic.music.BasicMusicStrategy;
import net.imshit.logic.music.MuteMusicStrategy;
import net.imshit.logic.paint.AbstractPaintStrategy;
import net.imshit.logic.paint.FancyPaintStrategy;
import net.imshit.logic.generate.enemy.AbstractEnemyGenerateStrategy;
import net.imshit.logic.generate.enemy.EasyEnemyGenerateStrategy;
import net.imshit.logic.generate.enemy.HardEnemyGenerateStrategy;
import net.imshit.logic.generate.enemy.MediumEnemyGenerateStrategy;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
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

    private final HeroAircraft heroAircraft = HeroAircraft.getInstance();
    private final List<AbstractEnemy> enemyAircraftObjects = new LinkedList<>();
    private final List<HeroBullet> heroBullets = new LinkedList<>();
    private final List<EnemyBullet> enemyBullets = new LinkedList<>();
    private final List<AbstractProp> enemyProps = new LinkedList<>();
    private final List<DyingAnimation> animations = new LinkedList<>();
    private AbstractEnemy boss;

    private final List<List<? extends AbstractFlyingObject>> elementLists = List.of(enemyAircraftObjects, heroBullets, enemyBullets, enemyProps, animations);
    private final List<List<? extends EnemyListener>> enemyListenerLists = List.of(enemyAircraftObjects, enemyBullets);

    private ScheduledExecutorService executorService;
    private final List<Callback<GamePanel>> callbacks = new LinkedList<>();

    private final AbstractPaintStrategy paintStrategy = new FancyPaintStrategy();
    private AbstractEnemyGenerateStrategy generateStrategy = new EasyEnemyGenerateStrategy();
    private AbstractMusicStrategy musicStrategy = new BasicMusicStrategy();

    private int score;
    private Difficulty difficulty;
    private int time;

    public GamePanel() {
        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);
    }

    public HeroAircraft getHeroAircraft() {
        return heroAircraft;
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
            case NOTSET -> {
            }
            case EASY -> {
                generateStrategy = new EasyEnemyGenerateStrategy();
                paintStrategy.setBackgroundImage(ImageManager.BACKGROUND_IMAGE_EASY);
            }
            case MEDIUM -> {
                generateStrategy = new MediumEnemyGenerateStrategy();
                paintStrategy.setBackgroundImage(ImageManager.BACKGROUND_IMAGE_MEDIUM);
            }
            case HARD -> {
                generateStrategy = new HardEnemyGenerateStrategy();
                paintStrategy.setBackgroundImage(ImageManager.BACKGROUND_IMAGE_HARD);
            }
        }
    }

    public void setMusicOn(boolean musicOn) {
        if (musicOn) {
            this.musicStrategy = new BasicMusicStrategy();
        } else {
            this.musicStrategy = new MuteMusicStrategy();
        }
    }

    public void addGameOverCallback(Callback<GamePanel> callback) {
        callbacks.add(callback);
    }

    public void notify(Event e) {
        this.musicStrategy.playBombExplosion();
        this.enemyListenerLists.stream().flatMap(Collection::stream).forEach(item -> item.notify(e));
        this.enemyAircraftObjects.stream().filter(AbstractFlyingObject::notValid).forEach(aircraft -> {
            this.animations.add(aircraft.getAnimation());
            this.score += aircraft.getCredits();
        });
    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action(SettingPanel settingPanel) {
        this.init(settingPanel.getDifficulty(), settingPanel.getMusicOn());
        this.executorService.scheduleWithFixedDelay(this::update, Config.REFRESH_INTERVAL, Config.REFRESH_INTERVAL, TimeUnit.MILLISECONDS);
    }

    private void init(Difficulty difficulty, boolean musicOn) {
        this.setDifficulty(difficulty);
        this.setMusicOn(musicOn);
        this.musicStrategy.setBgm(AbstractMusicStrategy.BgmType.NORMAL);

        this.executorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());
        this.heroAircraft.reset();
        this.elementLists.forEach(List::clear);
        this.generateStrategy.reset();
        this.boss = null;
        this.score = 0;
        this.time = 0;
    }

    private void update() {
        this.time += Config.REFRESH_INTERVAL;

        // 产生新敌机
        this.generateEnemy();
        // 发射子弹
        this.shootAction();
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

    private void generateEnemy() {
        // BOSS 机产生
        if (this.generateStrategy.isTimeToGenerateBoss(this.boss, this.score)) {
            this.boss = this.generateStrategy.generateBoss();
            this.enemyAircraftObjects.add(this.boss);
            this.musicStrategy.setBgm(AbstractMusicStrategy.BgmType.BOSS);
        }
        // 敌机产生
        if (this.generateStrategy.isTimeToGenerateEnemy(this.time)) {
            this.enemyAircraftObjects.addAll(this.generateStrategy.generateEnemy(enemyAircraftObjects.size()));
        }
    }

    private void shootAction() {
        // 敌机射出子弹
        if (this.generateStrategy.isTimeForEnemyShoot(this.time)) {
            this.enemyAircraftObjects.forEach(enemy -> this.enemyBullets.addAll(enemy.shoot()));
            this.musicStrategy.playBullet();
        }
        // 英雄机射出子弹
        if (this.generateStrategy.isTimeForHeroShoot(this.time)) {
            this.heroBullets.addAll(heroAircraft.shoot());
            this.musicStrategy.playBullet();
        }
    }

    private void moveAction() {
        this.elementLists.forEach(list -> list.forEach(AbstractFlyingObject::forward));
    }

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
                    animations.add(enemyAircraft.getAnimation());
                    score += enemyAircraft.getCredits();
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
            prop.activate(this);
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

    private void cleanInvalid() {
        this.elementLists.forEach((list -> list.removeIf(AbstractFlyingObject::notValid)));
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
        this.paintStrategy.draw(g, this.enemyBullets, this.enemyProps, this.heroBullets, this.enemyAircraftObjects, this.animations, this.heroAircraft, this.score);
    }
}
