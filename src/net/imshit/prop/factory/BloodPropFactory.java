package net.imshit.prop.factory;

import net.imshit.prop.AbstractProp;
import net.imshit.prop.BloodProp;

import java.util.List;

public class BloodPropFactory extends AbstractPropFactory{
    @Override
    public BloodProp createProp(int locationX, int locationY) {
        return new BloodProp(locationX, locationY, speedX, speedY);
    }
}
