package net.imshit.element.shoot;

import net.imshit.element.bullet.AbstractBullet;

import java.util.List;

/**
 * @author Jim
 */
public interface ShootStrategy {
    /**
     * 返回子弹列表
     *
     * @param x      飞机水平位置
     * @param y      飞机竖直位置
     * @param speedY 飞机竖直速度
     * @param power  飞机攻击力
     * @return 发射子弹列表
     */
    List<AbstractBullet> shoot(int x, int y, int speedY, int power);
}
