package net.imshit.element.shoot.enemy;

import net.imshit.element.bullet.EnemyBullet;

import java.util.List;

/**
 * 敌机不射击策略
 *
 * @author jim
 */
public class EnemyNoShootStrategy implements EnemyShootStrategy {
    @Override
    public List<EnemyBullet> shoot(float x, float y, float speedY, int power) {
        return List.of();
    }
}
