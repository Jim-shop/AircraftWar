package net.imshit.element.aircraft.enemy;

import net.imshit.element.aircraft.AbstractAircraft;
import net.imshit.element.prop.AbstractProp;
import net.imshit.element.shoot.enemy.EnemyShootStrategyFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jim
 */
public abstract class AbstractEnemy extends AbstractAircraft {

    public AbstractEnemy(float locationX, float locationY, float speedX, float speedY, int hp, int power, int shootNum) {
        super(locationX, locationY, speedX, speedY, hp, power, new EnemyShootStrategyFactory(), shootNum);
    }

    public List<AbstractProp> prop() {
        return new LinkedList<>();
    }
}
