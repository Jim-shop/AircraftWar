package net.imshit.element.aircraft.enemy;

import net.imshit.element.prop.AbstractProp;
import net.imshit.element.prop.factory.AbstractPropFactory;
import net.imshit.element.prop.factory.RandomPropFactory;

import java.util.List;

/**
 * 精英敌机
 * 可射击
 *
 * @author jim
 */
public class EliteEnemy extends AbstractEnemy {
    private final AbstractPropFactory propFactory = new RandomPropFactory();

    public EliteEnemy(float locationX, float locationY, float speedX, float speedY, int hp, int power) {
        super(locationX, locationY, speedX, speedY, hp, power, 1);
    }

    @Override
    public List<AbstractProp> prop() {
        var prop = propFactory.createProp(this.locationX, this.locationY);
        if (prop != null) {
            return List.of(prop);
        }
        return List.of();
    }
}
