package net.imshit.aircraft.enemy;

import net.imshit.bullet.AbstractBullet;
import net.imshit.bullet.EnemyBullet;
import net.imshit.prop.AbstractProp;
import net.imshit.prop.factory.AbstractPropFactory;
import net.imshit.prop.factory.RandomPropFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class EliteEnemy extends AbstractEnemy {

    private final int shootNum = 1;
    private final int power = 10;

    private final AbstractPropFactory propFactory = new RandomPropFactory();

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }


    /**
     * 通过射击产生子弹
     *
     * @return 射击出的子弹List
     */
    @Override
    public List<AbstractBullet> shoot() {
        List<AbstractBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + 2;
        int speedX = 0;
        int speedY = this.getSpeedY() + 5;
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            res.add(new EnemyBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY, power));
        }
        return res;
    }

    @Override
    public List<AbstractProp> prop() {
        var prop = propFactory.createProp(this.locationX, this.locationY);
        if (prop != null) {
            return List.of(prop);
        }
        return List.of();
    }
}
