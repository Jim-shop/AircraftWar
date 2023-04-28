package net.imshit.element.bullet;

import net.imshit.util.listener.EnemyListener;
import net.imshit.util.listener.Event;

import java.util.Objects;

/**
 * @author Jim
 */
public class EnemyBullet extends AbstractBullet implements EnemyListener {
    public EnemyBullet(float locationX, float locationY, float speedX, float speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }

    @Override
    public void notify(Event e) {
        if (Objects.requireNonNull(e) == Event.BOMB_ACTIVATE) {
            this.vanish();
        }
    }
}
