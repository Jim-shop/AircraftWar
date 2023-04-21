package net.imshit.element.prop.factory;

import net.imshit.element.prop.AbstractProp;

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
    public AbstractProp createProp(float locationX, float locationY) {
        var bloodProb = 0.3;
        var bombProb = 0.3;
        var bulletProb = 0.3;
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
