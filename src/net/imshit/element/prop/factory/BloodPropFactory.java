package net.imshit.element.prop.factory;

import net.imshit.element.prop.BloodProp;

/**
 * @author Jim
 */
public class BloodPropFactory extends AbstractPropFactory {
    @Override
    public BloodProp createProp(int locationX, int locationY) {
        return new BloodProp(locationX, locationY, speedX, speedY);
    }
}
