package net.imshit.prop.factory;

import net.imshit.prop.AbstractProp;
import net.imshit.prop.BloodProp;
import net.imshit.prop.BombProp;
import net.imshit.prop.BulletProp;

import java.util.LinkedList;
import java.util.List;

public class RandomPropFactory extends AbstractPropFactory {
    private final BloodPropFactory bloodFactory = new BloodPropFactory();
    private final BombPropFactory bombFactory = new BombPropFactory();
    private final BulletPropFactory bulletFactory = new BulletPropFactory();

    private final double bloodProb = 0.3;
    private final double bombProb = 0.3;
    private final double bulletProb = 0.3;

    @Override
    public AbstractProp createProp(int locationX, int locationY) {
        var rand = Math.random();
        if (rand < bloodProb) {
            return bloodFactory.createProp(locationX, locationY);
        } else if (rand < bloodProb + bombProb) {
            return bombFactory.createProp(locationX, locationY);
        } else if (rand < bloodProb + bombProb + bulletProb) {
            return bulletFactory.createProp(locationX, locationY);
        } else {
            return null;
        }
    }
}
