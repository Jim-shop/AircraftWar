package net.imshit.element.aircraft.enemy;

import net.imshit.Config;
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

    public BossEnemy(float locationX, float locationY, float speedX, float speedY, int hp, int power, int shootNum) {
        super(locationX, locationY, speedX, speedY, hp, power, shootNum);
    }

    @Override
    public void forward() {
        locationX += speedX * Config.REFRESH_INTERVAL;
        if (locationX < 0 || locationX >= Config.WINDOW_WIDTH) {
            speedX = -speedX;
        }
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
