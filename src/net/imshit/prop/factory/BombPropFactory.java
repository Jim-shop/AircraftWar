package net.imshit.prop.factory;

import net.imshit.prop.BombProp;

/**
 * @author Jim
 */
public class BombPropFactory extends AbstractPropFactory {
    @Override
    public BombProp createProp(int locationX, int locationY) {
        return new BombProp(locationX, locationY, speedX, speedY);
    }
}
