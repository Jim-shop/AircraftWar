package net.imshit.shoot.hero;

import net.imshit.bullet.AbstractBullet;
import net.imshit.bullet.HeroBullet;

import java.util.List;

/**
 * 英雄机直射策略
 * @author Jim
 */
public class HeroDirectShootStrategy implements HeroShootStrategy {
    @Override
    public List<AbstractBullet> shoot(int x, int y, int speedY, int power) {
        int direction = -1;
        int bulletX = x;
        int bulletY = y + direction * 2;
        int bulletSpeedX = 0;
        int bulletSpeedY = speedY + direction * 10;
        return List.of(new HeroBullet(bulletX, bulletY, bulletSpeedX, bulletSpeedY, power));
    }
}
