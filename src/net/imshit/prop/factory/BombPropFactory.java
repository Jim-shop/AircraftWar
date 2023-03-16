package net.imshit.prop.factory;

import net.imshit.prop.AbstractProp;
import net.imshit.prop.BombProp;

public class BombPropFactory extends AbstractPropFactory {
    @Override
    public BombProp createProp(int locationX, int locationY) {
        return new BombProp(locationX, locationY, speedX, speedY);
    }
}
