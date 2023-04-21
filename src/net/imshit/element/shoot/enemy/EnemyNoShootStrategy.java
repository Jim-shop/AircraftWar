package net.imshit.element.shoot.enemy;

import net.imshit.element.bullet.AbstractBullet;

import java.util.List;

/**
 * 敌机不射击策略
 * @author jim
 */
public class EnemyNoShootStrategy implements EnemyShootStrategy {
    @Override
    public List<AbstractBullet> shoot(int x, int y, int speedY, int power) {
        return List.of();
    }
}
