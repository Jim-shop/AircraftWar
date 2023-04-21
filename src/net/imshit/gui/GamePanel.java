package net.imshit.gui;

import net.imshit.element.aircraft.AbstractAircraft;
import net.imshit.element.aircraft.enemy.AbstractEnemy;
import net.imshit.element.aircraft.enemy.BossEnemy;
import net.imshit.element.aircraft.enemy.factory.AbstractEnemyFactory;
import net.imshit.element.aircraft.enemy.factory.BossEnemyFactory;
import net.imshit.element.aircraft.enemy.factory.RandomEnemyFactory;
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
    private final ScheduledExecutorService executorService;
    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private final int timeInterval = 1000 / 45;
    private final HeroAircraft heroAircraft;
    private final List<AbstractEnemy> enemyAircraftObjects;
    private final List<AbstractBullet> heroBullets;
    private final List<AbstractBullet> enemyBullets;
    private final List<AbstractProp> enemyProps;
    /**
     * 屏幕中出现的敌机最大数量
     */
    private final int enemyMaxNumber = 5;
    /**
     * 多少分之后出现 BOSS
     */
    private final int bossScoreThreshold = 300;
    /**
     * 生成 BOSS 概率
     */
    private final double bossProb = 0.05;
    /**
     * 敌机工厂
     */
    private final AbstractEnemyFactory enemyFactory = new RandomEnemyFactory();
    private final BossEnemyFactory bossFactory = new BossEnemyFactory();
    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private final int cycleDuration = 600;
    private final List<GameOverCallback> callbacks;
    /**
     * BOSS 机
     */
    private BossEnemy boss = null;

    /**
     * 当前得分
     */
    private int score = 0;
    /**
     * 当前时刻
     */
    private int cycleTime = 0;
    private int time = 0;
    private Difficulty difficulty;
    private AbstractGenerateStrategy generateStrategy = new EasyGenerateStrategy();
    private final AbstractPaintStrategy paintStrategy = new SimplePaintStrategy();
    private AbstractMusicStrategy musicStrategy = new BasicMusicStrategy();

    public GamePanel() {
        heroAircraft = HeroAircraft.getInstance();
        enemyAircraftObjects = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        enemyProps = new LinkedList<>();

        callbacks = new LinkedList<>();

        /*
          Scheduled 线程池，用于定时任务调度
          关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
          apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

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
                // 不应该执行到这
                generateStrategy = new EasyGenerateStrategy();
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

            time += timeInterval;

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
//                enemyAircraftObjects.addAll(generateStrategy.generateEnemy(enemyAircraftObjects.size()));
                // BOSS 机产生
                if (score > bossScoreThreshold && boss == null && Math.random() < bossProb) {
                    boss = bossFactory.createEnemy();
                    enemyAircraftObjects.add(boss);
                    musicStrategy.setBgm(AbstractMusicStrategy.BgmType.BOSS);
                }
                // 新敌机产生
                if (enemyAircraftObjects.size() < enemyMaxNumber) {
                    enemyAircraftObjects.add(enemyFactory.createEnemy());
                }
                // 飞机射出子弹
                shootAction();
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftObjectsMoveAction();

            // 凋落物移动
            propsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

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

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

        // 启动 BGM
        musicStrategy.setBgm(AbstractMusicStrategy.BgmType.NORMAL);
    }

    private void shootAction() {
        // 敌机射击
        for (var enemy : enemyAircraftObjects) {
            enemyBullets.addAll(enemy.shoot());
        }

        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());

        musicStrategy.playBullet();
    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // 敌机子弹攻击英雄
        for (var bullet : enemyBullets) {
            if (heroAircraft.crash(bullet)) {
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
                musicStrategy.playBulletHit();
            }
        }

        // 英雄子弹攻击敌机
        for (AbstractBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (var enemyAircraft : enemyAircraftObjects) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        enemyProps.addAll(enemyAircraft.prop());
                        score += 30;
                    }
                    musicStrategy.playBulletHit();
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // 我方获得道具，道具生效
        for (var prop : enemyProps) {
            if (heroAircraft.crash((prop))) {
                prop.use(heroAircraft);
                prop.vanish();
                musicStrategy.playGetSupply();
            }
        }
    }

    private void bulletsMoveAction() {
        for (AbstractBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (AbstractBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftObjectsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircraftObjects) {
            enemyAircraft.forward();
        }
    }

    private void propsMoveAction() {
        for (var enemyProp : enemyProps) {
            enemyProp.forward();
        }
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
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
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
