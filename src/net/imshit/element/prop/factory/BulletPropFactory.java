package net.imshit.element.prop.factory;

import net.imshit.element.prop.BulletProp;

/**
 * @author Jim
 */
public class BulletPropFactory extends AbstractPropFactory {
    @Override
    public BulletProp createProp(int locationX, int locationY) {
        return new BulletProp(locationX, locationY, speedX, speedY);
    }
}
