package net.imshit.logic.game.paint;

import net.imshit.Config;
import net.imshit.element.aircraft.hero.HeroAircraft;
import net.imshit.element.basic.AbstractFlyingObject;
import net.imshit.io.resource.ImageManager;

import java.awt.*;
import java.util.List;

/**
 * 简单界面绘制策略
 *
 * @author Jim
 */
public class SimplePaintStrategy extends AbstractPaintStrategy {

    private float backGroundTop = 0;

    private void paintObjects(Graphics g, List<? extends AbstractFlyingObject> objects) {
        for (var object : objects) {
            var image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, (int) (object.getLocationX() - image.getWidth() / 2), (int) (object.getLocationY() - image.getHeight() / 2), null);
        }
    }

    private void paintScoreAndLife(Graphics g, HeroAircraft heroAircraft, int score) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(0xFF0000));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + heroAircraft.getHp(), x, y);
    }


    @Override
    public void draw(Graphics g, List<? extends AbstractFlyingObject> enemyBullets, List<? extends AbstractFlyingObject> enemyProps, List<? extends AbstractFlyingObject> heroBullets, List<? extends AbstractFlyingObject> enemyAircraftObjects, HeroAircraft heroAircraft, int score) {
        // 绘制背景,图片滚动
        g.drawImage(this.backgroundImage, 0, (int) this.backGroundTop - Config.WINDOW_HEIGHT, null);
        g.drawImage(this.backgroundImage, 0, (int) this.backGroundTop, null);
        this.backGroundTop += 0.005 * Config.REFRESH_INTERVAL;
        if ((int) this.backGroundTop >= Config.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        this.paintObjects(g, enemyBullets);
        this.paintObjects(g, enemyProps);
        this.paintObjects(g, heroBullets);
        this.paintObjects(g, enemyAircraftObjects);

        // 英雄机
        g.drawImage(ImageManager.HERO_IMAGE, (int) (heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2), (int) (heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2), null);

        //绘制得分和生命值
        this.paintScoreAndLife(g, heroAircraft, score);
    }
}
