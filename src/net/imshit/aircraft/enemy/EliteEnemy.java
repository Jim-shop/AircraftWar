package net.imshit.aircraft.enemy;

import net.imshit.prop.AbstractProp;
import net.imshit.prop.factory.AbstractPropFactory;
import net.imshit.prop.factory.RandomPropFactory;

import java.util.List;

/**
 * 精英敌机
 * 可射击
 *
 * @author jim
 */
public class EliteEnemy extends AbstractEnemy {
    private final AbstractPropFactory propFactory = new RandomPropFactory();

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp, 20, 1);
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
