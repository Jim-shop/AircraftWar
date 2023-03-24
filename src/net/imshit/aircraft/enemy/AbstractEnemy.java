package net.imshit.aircraft.enemy;

import net.imshit.aircraft.AbstractAircraft;
import net.imshit.application.Main;
import net.imshit.bullet.AbstractBullet;
import net.imshit.bullet.EnemyBullet;
import net.imshit.prop.AbstractProp;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jim
 */
public abstract class AbstractEnemy extends AbstractAircraft {

    protected int shootNum = 0;
    protected int power = 10;

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
        List<AbstractBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + 2;
        int speedY = this.getSpeedY() + 5;
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            res.add(new EnemyBullet(x + (i * 2 - shootNum + 1) * 10, y, (i * 2 - shootNum + 1), speedY, power));
        }
        return res;
    }

    public List<AbstractProp> prop() {
        return new LinkedList<>();
    }
}
