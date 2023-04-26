package net.imshit.element.prop;

import net.imshit.gui.GamePanel;

/**
 * @author Jim
 */
public class BombProp extends AbstractProp {

    public BombProp(float locationX, float locationY, float speedX, float speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activate(GamePanel game) {
        System.out.println("BombSupply active!");
    }
}