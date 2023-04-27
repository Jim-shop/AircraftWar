package net.imshit.element.shoot.enemy;

import net.imshit.element.bullet.EnemyBullet;

import java.util.List;

/**
 * 敌机直射策略
 *
 * @author Jim
 */
public class EnemyDirectShootStrategy implements EnemyShootStrategy {
    @Override
    public List<EnemyBullet> shoot(float x, float y, float speedY, int power) {
        int direction = 1;
        float bulletY = y + direction * 2;
        float bulletSpeedX = 0;
        float bulletSpeedY = speedY + direction * 0.1f;
        return List.of(new EnemyBullet(x, bulletY, bulletSpeedX, bulletSpeedY, power));
    }
}
