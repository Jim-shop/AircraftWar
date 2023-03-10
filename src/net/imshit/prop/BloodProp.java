package net.imshit.prop;

import net.imshit.aircraft.HeroAircraft;

public class BloodProp extends AbstractProp {

    public BloodProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void use(HeroAircraft hero) {
        hero.increaseHp(30);
    }
}