/*
 * Copyright (c) 2023 Jim-shop
 * AircraftWar is licensed under Mulan PubL v2.
 * You can use this software according to the terms and conditions of the Mulan PubL v2.
 * You may obtain a copy of Mulan PubL v2 at:
 *          http://license.coscl.org.cn/MulanPubL-2.0
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PubL v2 for more details.
 */

package net.imshit.util.paint;

import net.imshit.Config;
import net.imshit.element.AbstractFlyingObject;
import net.imshit.element.aircraft.AbstractAircraft;
import net.imshit.element.aircraft.enemy.AbstractEnemy;
import net.imshit.element.aircraft.hero.HeroAircraft;
import net.imshit.element.animation.DyingAnimation;
import net.imshit.element.bullet.AbstractBullet;
import net.imshit.element.bullet.EnemyBullet;
import net.imshit.element.bullet.HeroBullet;
import net.imshit.element.prop.AbstractProp;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * 华丽界面绘制策略
 *
 * @author Jim
 */
public class FancyPaintStrategy extends AbstractPaintStrategy {

    public static final int BAR_OFFSET = 5;
    public static final Font BAR_FONT = new Font("SansSerif", Font.PLAIN, 10);
    public static final int BAR_TEXT_OFFSET = 5;
    public static final int SCORE_X = 10;
    public static final int SCORE_Y = 25;
    public static final Font SCORE_FONT = new Font("SansSerif", Font.BOLD, 22);
    private static final int BAR_LENGTH = 20;
    private static final int BAR_HEIGHT = 3;
    private float backGroundTop = 0;

    private void paintObject(Graphics g, AbstractFlyingObject object) {
        var image = object.getImage();
        var centerX = (int) object.getLocationX();
        var centerY = (int) object.getLocationY();
        var leftUpX = centerX - image.getWidth() / 2;
        var leftUpY = centerY - image.getHeight() / 2;
        g.drawImage(image, leftUpX, leftUpY, null);
    }

    private void paintLife(Graphics g, AbstractAircraft<? extends AbstractBullet> aircraft) {
        var hp = aircraft.getHp();
        var maxHp = aircraft.getMaxHp();
        var barStartX = (int) aircraft.getLocationX() - BAR_LENGTH / 2;
        var barTopY = (int) (aircraft.getLocationY() - aircraft.getHeight() / 2) - BAR_OFFSET;
        g.setColor(Color.BLACK);
        g.drawRect(barStartX, barTopY, BAR_LENGTH, BAR_HEIGHT);
        g.setColor(Color.WHITE);
        g.fillRect(barStartX, barTopY, BAR_LENGTH, BAR_HEIGHT);
        g.setColor(Color.RED);
        g.fillRect(barStartX, barTopY, BAR_LENGTH * hp / maxHp, BAR_HEIGHT);
        g.setFont(BAR_FONT);
        g.drawString(String.format("%d/%d", hp, maxHp), barStartX, barTopY - BAR_TEXT_OFFSET);
    }

    private void paintScoreAndLife(Graphics g, int score) {
        g.setColor(Color.ORANGE);
        g.setFont(SCORE_FONT);
        g.drawString("SCORE:" + score, SCORE_X, SCORE_Y);
    }


    @Override
    public void draw(Graphics g, List<EnemyBullet> enemyBullets, List<AbstractProp> enemyProps, List<HeroBullet> heroBullets, List<AbstractEnemy> enemyAircraftObjects, List<DyingAnimation> animations, HeroAircraft heroAircraft, int score) {
        // 绘制背景,图片滚动
        g.drawImage(this.backgroundImage, 0, (int) this.backGroundTop - Config.WINDOW_HEIGHT, null);
        g.drawImage(this.backgroundImage, 0, (int) this.backGroundTop, null);
        this.backGroundTop += 0.005 * Config.REFRESH_INTERVAL;
        if ((int) this.backGroundTop >= Config.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 先绘制子弹，后绘制飞机
        // 这样子弹显示在飞机的下层
        Stream.of(animations, enemyProps, enemyBullets, heroBullets, enemyAircraftObjects, List.of(heroAircraft)).flatMap(Collection::stream).forEach(object -> this.paintObject(g, object));
        // 绘制血条
        Stream.of(enemyAircraftObjects, List.of(heroAircraft)).flatMap(Collection::stream).forEach(aircraft -> this.paintLife(g, aircraft));
        //绘制得分和生命值
        this.paintScoreAndLife(g, score);
    }
}
