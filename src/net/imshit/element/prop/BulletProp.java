package net.imshit.element.prop;

import net.imshit.element.aircraft.hero.HeroAircraft;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jim
 */
public class BulletProp extends AbstractProp {

    private final AtomicInteger usedCount = new AtomicInteger(0);

    public BulletProp(float locationX, float locationY, float speedX, float speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void use(HeroAircraft hero) {
        usedCount.getAndIncrement();
        hero.setShootNum(3);
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            if (usedCount.decrementAndGet() == 0) {
                hero.setShootNum(1);
            }
        }).start();
    }
}