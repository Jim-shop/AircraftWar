package net.imshit.element.prop;

import net.imshit.element.aircraft.hero.HeroAircraft;

/**
 * @author Jim
 */
public class BloodProp extends AbstractProp {

    public BloodProp(float locationX, float locationY, float speedX, float speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void use(HeroAircraft hero) {
        hero.increaseHp(100);
    }
}