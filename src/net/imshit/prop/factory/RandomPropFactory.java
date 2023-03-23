package net.imshit.prop.factory;

import net.imshit.prop.AbstractProp;

/**
 * 随机道具工厂
 *
 * @author Jim
 */
public class RandomPropFactory extends AbstractPropFactory {
    private final BloodPropFactory bloodFactory = new BloodPropFactory();
    private final BombPropFactory bombFactory = new BombPropFactory();
    private final BulletPropFactory bulletFactory = new BulletPropFactory();

    @Override
    public AbstractProp createProp(int locationX, int locationY) {
        double bloodProb = 0.3;
        double bombProb = 0.3;
        double bulletProb = 0.3;
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
