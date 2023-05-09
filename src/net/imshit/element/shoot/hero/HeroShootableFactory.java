package net.imshit.element.shoot.hero;

import net.imshit.element.bullet.HeroBullet;
import net.imshit.element.shoot.AbstractShootableFactory;
import net.imshit.element.shoot.Shootable;

/**
 * 英雄机策略工厂
 *
 * @author Jim
 */
public class HeroShootableFactory extends AbstractShootableFactory<HeroBullet> {
    @Override
    public Shootable<HeroBullet> getStrategy(int shootNum) {
        if (shootNum == 3) {
            return new HeroScatterShootable();
        } else {
            return new HeroDirectShootable();
        }
    }
}
