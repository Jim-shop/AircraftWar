package net.imshit.prop.factory;

import net.imshit.prop.BulletProp;

public class BulletPropFactory extends AbstractPropFactory {
    @Override
    public BulletProp createProp(int locationX, int locationY) {
        return new BulletProp(locationX, locationY, speedX, speedY);
    }
}
