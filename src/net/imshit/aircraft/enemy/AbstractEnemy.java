package net.imshit.aircraft.enemy;

import net.imshit.aircraft.AbstractAircraft;
import net.imshit.application.Main;
import net.imshit.bullet.AbstractBullet;
import net.imshit.bullet.EnemyBullet;
import net.imshit.prop.AbstractProp;
import net.imshit.shoot.enemy.EnemyShootStrategyFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jim
 */
public abstract class AbstractEnemy extends AbstractAircraft {

    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int power, int shootNum) {
        super(locationX, locationY, speedX, speedY, hp, power, new EnemyShootStrategyFactory(), shootNum);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT) {
            vanish();
        }
    }

    public List<AbstractProp> prop() {
        return new LinkedList<>();
    }
}
