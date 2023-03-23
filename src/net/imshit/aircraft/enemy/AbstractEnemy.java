package net.imshit.aircraft.enemy;

import net.imshit.aircraft.AbstractAircraft;
import net.imshit.application.Main;
import net.imshit.bullet.AbstractBullet;
import net.imshit.prop.AbstractProp;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jim
 */
public abstract class AbstractEnemy extends AbstractAircraft {

    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT) {
            vanish();
        }
    }

    @Override
    public List<AbstractBullet> shoot() {
        return new LinkedList<>();
    }

    public List<AbstractProp> prop() {
        return new LinkedList<>();
    }
}
