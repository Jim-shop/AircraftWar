package net.imshit.util.generate.prop;

import net.imshit.element.prop.AbstractProp;
import net.imshit.element.prop.factory.AbstractPropFactory;
import net.imshit.element.prop.factory.BloodPropFactory;
import net.imshit.element.prop.factory.BombPropFactory;
import net.imshit.element.prop.factory.BulletPropFactory;

/**
 * 随机道具工厂
 *
 * @author Jim
 */
public abstract class AbstractPropGenerateStrategy extends AbstractPropFactory {
    protected double bloodProbability;
    protected double bombProbability;
    protected double bulletProbability;
    protected final BloodPropFactory bloodFactory = new BloodPropFactory();
    protected final BombPropFactory bombFactory = new BombPropFactory();
    protected final BulletPropFactory bulletFactory = new BulletPropFactory();

    @Override
    public AbstractProp createProp(float locationX, float locationY) {
        var rand = Math.random();
        if (rand < bloodProbability) {
            return bloodFactory.createProp(locationX, locationY);
        } else if (rand < bloodProbability + bombProbability) {
            return bombFactory.createProp(locationX, locationY);
        } else if (rand < bloodProbability + bombProbability + bulletProbability) {
            return bulletFactory.createProp(locationX, locationY);
        } else {
            return null;
        }
    }

    public AbstractProp createPropNotNull(float locationX, float locationY) {
        var rand = Math.random() / (bloodProbability + bombProbability + bulletProbability);
        if (rand < bloodProbability) {
            return bloodFactory.createProp(locationX, locationY);
        } else if (rand < bloodProbability + bombProbability) {
            return bombFactory.createProp(locationX, locationY);
        } else {
            return bulletFactory.createProp(locationX, locationY);
        }
    }
}