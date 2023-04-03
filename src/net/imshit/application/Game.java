package net.imshit.application;

import net.imshit.aircraft.AbstractAircraft;
import net.imshit.aircraft.enemy.AbstractEnemy;
import net.imshit.aircraft.enemy.BossEnemy;
import net.imshit.aircraft.enemy.factory.AbstractEnemyFactory;
import net.imshit.aircraft.enemy.factory.BossEnemyFactory;
import net.imshit.aircraft.enemy.factory.RandomEnemyFactory;
import net.imshit.aircraft.hero.HeroAircraft;
import net.imshit.basic.AbstractFlyingObject;
import net.imshit.bullet.AbstractBullet;
import net.imshit.prop.AbstractProp;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
public class Game extends JPanel {

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
     * BOSS 机
     */
    private BossEnemy boss = null;
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
    private int backGroundTop = 0;
    /**
     * 当前得分
     */
    private int score = 0;
    /**
     * 当前时刻
     */
    private int time = 0;
    private int cycleTime = 0;

    /**
     * 游戏结束标志
     */
    private boolean gameOverFlag = false;

    public Game() {
        heroAircraft = HeroAircraft.getInstance();
        enemyAircraftObjects = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        enemyProps = new LinkedList<>();

        /*
          Scheduled 线程池，用于定时任务调度
          关于alibaba code guide：可命名的 ThreadFactory 一般需要第三方包
          apache 第三方库： org.apache.commons.lang3.concurrent.BasicThreadFactory
         */
        this.executorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

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
                System.out.println(time);
                // BOSS 机产生
                if (score > bossScoreThreshold && boss == null && Math.random() < bossProb) {
                    boss = bossFactory.createEnemy();
                    enemyAircraftObjects.add(bossFactory.createEnemy());
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
            if (boss != null && boss.getHp() <= 0) {
                boss = null;
            }

            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");
            }

        };

        /*
          以固定延迟时间进行执行
          本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // 敌机射击
        for (var enemy : enemyAircraftObjects) {
            enemyBullets.addAll(enemy.shoot());
        }

        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
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
            }
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


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, enemyProps);
        paintImageWithPositionRevised(g, heroBullets);

        paintImageWithPositionRevised(g, enemyAircraftObjects);

        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2, heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2, object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }


}
