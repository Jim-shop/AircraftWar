package net.imshit.element.prop;

import net.imshit.gui.GamePanel;

/**
 * @author Jim
 */
public class BloodProp extends AbstractProp {

    public BloodProp(float locationX, float locationY, float speedX, float speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activate(GamePanel game) {
        game.getHeroAircraft().increaseHp(100);
    }
}