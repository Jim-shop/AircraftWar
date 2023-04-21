package net.imshit.element.prop;

import net.imshit.element.aircraft.hero.HeroAircraft;

/**
 * @author Jim
 */
public class BloodProp extends AbstractProp {

    public BloodProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void use(HeroAircraft hero) {
        hero.increaseHp(100);
    }
}