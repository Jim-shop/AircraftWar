package net.imshit.shoot.enemy;

import net.imshit.bullet.AbstractBullet;

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
