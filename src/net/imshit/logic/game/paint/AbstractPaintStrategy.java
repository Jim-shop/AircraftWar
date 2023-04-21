package net.imshit.logic.game.paint;

import net.imshit.element.aircraft.hero.HeroAircraft;
import net.imshit.element.basic.AbstractFlyingObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * 界面绘制策略
 *
 * @author Jim
 */
public abstract class AbstractPaintStrategy {

    protected BufferedImage backgroundImage;

    /**
     * 绘制游戏界面
     *
     * @param g                    图形句柄
     * @param enemyBullets         敌机子弹列表
     * @param enemyProps           敌机掉落物列表
     * @param heroBullets          英雄机子弹列表
     * @param enemyAircraftObjects 敌机列表
     * @param heroAircraft         英雄机实例
     * @param score                得分
     */
    public abstract void draw(Graphics g, java.util.List<? extends AbstractFlyingObject> enemyBullets, java.util.List<? extends AbstractFlyingObject> enemyProps, java.util.List<? extends AbstractFlyingObject> heroBullets, List<? extends AbstractFlyingObject> enemyAircraftObjects, HeroAircraft heroAircraft, int score);

    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
