package net.imshit.shoot.enemy;

import net.imshit.bullet.AbstractBullet;
import net.imshit.bullet.EnemyBullet;

import java.util.List;

/**
 * 敌机直射策略
 * @author Jim
 */
public class EnemyDirectShootStrategy implements EnemyShootStrategy {
    @Override
    public List<AbstractBullet> shoot(int x, int y, int speedY, int power) {
        int direction = 1;
        int bulletX = x;
        int bulletY = y + direction * 2;
        int bulletSpeedX = 0;
        int bulletSpeedY = speedY + direction * 10;
        return List.of(new EnemyBullet(bulletX, bulletY, bulletSpeedX, bulletSpeedY, power));
    }
}
