package net.imshit.prop;

import net.imshit.aircraft.HeroAircraft;

public class BulletProp extends AbstractProp {

    public BulletProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void use(HeroAircraft hero) {
        System.out.println("BombSupply active!");
    }
}