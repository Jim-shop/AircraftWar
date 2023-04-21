package net.imshit.element.prop.factory;

import net.imshit.element.prop.BulletProp;

/**
 * @author Jim
 */
public class BulletPropFactory extends AbstractPropFactory {
    @Override
    public BulletProp createProp(float locationX, float locationY) {
        return new BulletProp(locationX, locationY, speedX, speedY);
    }
}
