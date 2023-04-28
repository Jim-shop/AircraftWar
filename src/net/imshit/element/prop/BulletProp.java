package net.imshit.element.prop;

import net.imshit.gui.GamePanel;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jim
 */
public class BulletProp extends AbstractProp {

    private static final AtomicInteger USED_COUNT = new AtomicInteger(0);

    public BulletProp(float locationX, float locationY, float speedX, float speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activate(GamePanel game) {
        USED_COUNT.getAndIncrement();
        var heroAircraft = game.getHeroAircraft();
        heroAircraft.setShootNum(3);
        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ignored) {
            }
            if (USED_COUNT.decrementAndGet() == 0) {
                heroAircraft.setShootNum(1);
            }
        }).start();
    }
}