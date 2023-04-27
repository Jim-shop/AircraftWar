package net.imshit.element.aircraft.enemy;

import net.imshit.element.aircraft.AbstractAircraft;
import net.imshit.element.bullet.EnemyBullet;
import net.imshit.element.prop.AbstractProp;
import net.imshit.element.shoot.enemy.EnemyShootStrategyFactory;
import net.imshit.logic.listener.EnemyListener;
import net.imshit.logic.listener.Event;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jim
 */
public abstract class AbstractEnemy extends AbstractAircraft<EnemyBullet> implements EnemyListener {

    public AbstractEnemy(float locationX, float locationY, float speedX, float speedY, int hp, int power, int shootNum) {
        super(locationX, locationY, speedX, speedY, hp, power, new EnemyShootStrategyFactory(), shootNum);
    }

    public List<AbstractProp> prop() {
        return new LinkedList<>();
    }

    public int getCredits() {
        return 0;
    }

    @Override
    public void notify(Event e) {
            switch (e) {
                case BOMB_ACTIVATE -> this.vanish();
                default -> {
                }
            }
    }
}
