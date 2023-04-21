package net.imshit.element.aircraft.enemy;

import net.imshit.element.prop.AbstractProp;
import net.imshit.element.prop.factory.AbstractPropFactory;
import net.imshit.element.prop.factory.RandomPropFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Boss敌机
 * 可散射射击
 *
 * @author jim
 */
public class BossEnemy extends AbstractEnemy {

    private final AbstractPropFactory propFactory = new RandomPropFactory();

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int power) {
        super(locationX, locationY, speedX, speedY, hp, power, 3);
    }

    @Override
    public List<AbstractProp> prop() {
        var props = new LinkedList<AbstractProp>();
        for (var i = -1; i <= 1; i++) {
            AbstractProp prop = null;
            while (prop == null) {
                prop = propFactory.createProp(this.locationX + i * 20, this.locationY - Math.abs(i) * 10);
            }
            props.add(prop);
        }
        return props;
    }
}
