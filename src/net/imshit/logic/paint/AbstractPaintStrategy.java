package net.imshit.logic.paint;

import net.imshit.element.aircraft.enemy.AbstractEnemy;
import net.imshit.element.aircraft.hero.HeroAircraft;
import net.imshit.element.animation.DyingAnimation;
import net.imshit.element.bullet.EnemyBullet;
import net.imshit.element.bullet.HeroBullet;
import net.imshit.element.prop.AbstractProp;

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

    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * 绘制游戏界面
     *
     * @param g                    图形句柄
     * @param enemyBullets         敌机子弹列表
     * @param enemyProps           敌机掉落物列表
     * @param heroBullets          英雄机子弹列表
     * @param enemyAircraftObjects 敌机列表
     * @param animations           动画列表
     * @param heroAircraft         英雄机实例
     * @param score                得分
     */
    public abstract void draw(Graphics g, List<EnemyBullet> enemyBullets, List<AbstractProp> enemyProps, List<HeroBullet> heroBullets, List<AbstractEnemy> enemyAircraftObjects, List<DyingAnimation> animations, HeroAircraft heroAircraft, int score);
}
