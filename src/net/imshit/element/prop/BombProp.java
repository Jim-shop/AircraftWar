package net.imshit.element.prop;

import net.imshit.element.aircraft.hero.HeroAircraft;

/**
 * @author Jim
 */
public class BombProp extends AbstractProp {

    public BombProp(float locationX, float locationY, float speedX, float speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void use(HeroAircraft hero) {
        System.out.println("FireSupply active!");
    }
}