package net.imshit.element.prop.factory;

import net.imshit.element.prop.BombProp;

/**
 * @author Jim
 */
public class BombPropFactory extends AbstractPropFactory {
    @Override
    public BombProp createProp(float locationX, float locationY) {
        return new BombProp(locationX, locationY, speedX, speedY);
    }
}
