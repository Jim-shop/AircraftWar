package net.imshit.element.shoot.hero;

import net.imshit.element.aircraft.hero.HeroAircraft;
import net.imshit.element.bullet.AbstractBullet;
import net.imshit.element.bullet.HeroBullet;

import java.util.List;

/**
 * 英雄机直射策略
 *
 * @author Jim
 */
public class HeroDirectShootStrategy implements HeroShootStrategy {
    @Override
    public List<HeroBullet> shoot(float x, float y, float speedY, int power) {
        int direction = -1;
        float bulletY = y + direction * 2;
        float bulletSpeedX = 0;
        float bulletSpeedY = speedY + direction * 0.2f;
        return List.of(new HeroBullet(x, bulletY, bulletSpeedX, bulletSpeedY, power));
    }
}
