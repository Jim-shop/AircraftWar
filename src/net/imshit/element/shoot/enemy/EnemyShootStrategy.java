package net.imshit.element.shoot.enemy;

import net.imshit.element.bullet.AbstractBullet;
import net.imshit.element.bullet.EnemyBullet;
import net.imshit.element.shoot.ShootStrategy;

import java.util.List;

/**
 * 敌机策略
 *
 * @author Jim
 */
public interface EnemyShootStrategy extends ShootStrategy<EnemyBullet> {
    /**
     * 敌机设计接口
     * @param x      飞机水平位置
     * @param y      飞机竖直位置
     * @param speedY 飞机竖直速度
     * @param power  飞机攻击力
     * @return 敌机子弹
     */
    @Override
    List<EnemyBullet> shoot(float x, float y, float speedY, int power);
}
