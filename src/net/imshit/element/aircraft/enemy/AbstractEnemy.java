package net.imshit.element.aircraft.enemy;

import net.imshit.element.aircraft.AbstractAircraft;
import net.imshit.element.bullet.EnemyBullet;
import net.imshit.element.prop.AbstractProp;
import net.imshit.element.shoot.enemy.EnemyShootableFactory;
import net.imshit.util.listener.EnemyListener;
import net.imshit.util.listener.Event;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Jim
 */
public abstract class AbstractEnemy extends AbstractAircraft<EnemyBullet> implements EnemyListener {

    public AbstractEnemy(float locationX, float locationY, float speedX, float speedY, int hp, int power, int shootNum) {
        super(locationX, locationY, speedX, speedY, hp, power, new EnemyShootableFactory(), shootNum);
    }

    public List<AbstractProp> prop() {
        return new LinkedList<>();
    }

    public int getCredits() {
        return 0;
    }

    @Override
    public void notify(Event e) {
        if (Objects.requireNonNull(e) == Event.BOMB_ACTIVATE) {
            this.vanish();
        }
    }
}
