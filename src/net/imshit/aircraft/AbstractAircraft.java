package net.imshit.aircraft;

import net.imshit.basic.AbstractFlyingObject;
import net.imshit.bullet.AbstractBullet;
import net.imshit.shoot.AbstractShootStrategyFactory;
import net.imshit.shoot.ShootStrategy;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected final int maxHp;
    private final int power;
    protected int hp;
    protected ShootStrategy shootStrategy;
    protected final AbstractShootStrategyFactory shootStrategyFactory;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp, int power, AbstractShootStrategyFactory strategyFactory, int shootNum) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
        this.power = power;
        this.shootStrategyFactory = strategyFactory;
        this.shootStrategy = strategyFactory.getStrategy(shootNum);
    }

    public void setShootNum(int shootNum) {
        this.shootStrategy = this.shootStrategyFactory.getStrategy(shootNum);
    }

    public void decreaseHp(int decrease) {
        if (decrease > 0) {
            hp -= decrease;
            if (hp <= 0) {
                hp = 0;
                vanish();
            }
        }
    }

    public int getHp() {
        return hp;
    }


    /**
     * 飞机射击方法
     *
     * @return 返回射出的子弹列表（可为空）
     */
    public List<AbstractBullet> shoot() {
        return this.shootStrategy.shoot(this.locationX, this.locationY, this.speedY, this.power);
    }

}


