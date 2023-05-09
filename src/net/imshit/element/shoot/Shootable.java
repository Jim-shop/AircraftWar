package net.imshit.element.shoot;

import net.imshit.element.bullet.AbstractBullet;

import java.util.List;

/**
 * @author Jim
 */
public interface Shootable<T extends AbstractBullet> {
    /**
     * 返回子弹列表
     *
     * @param x      飞机水平位置
     * @param y      飞机竖直位置
     * @param speedY 飞机竖直速度
     * @param power  飞机攻击力
     * @return 发射子弹列表
     */
    List<T> shoot(float x, float y, float speedY, int power);
}
