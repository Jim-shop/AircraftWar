package net.imshit.element.bullet;

import net.imshit.logic.listener.EnemyListener;
import net.imshit.logic.listener.Event;

/**
 * @author Jim
 */
public class EnemyBullet extends AbstractBullet implements EnemyListener {
    public EnemyBullet(float locationX, float locationY, float speedX, float speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
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
