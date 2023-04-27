package net.imshit.element.aircraft;

import net.imshit.element.AbstractFlyingObject;
import net.imshit.element.animation.DyingAnimation;
import net.imshit.element.bullet.AbstractBullet;
import net.imshit.element.shoot.AbstractShootStrategyFactory;
import net.imshit.element.shoot.ShootStrategy;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author jim
 */
public abstract class AbstractAircraft<T extends AbstractBullet> extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected final int maxHp;
    protected int hp;
    protected final int power;
    protected final AbstractShootStrategyFactory<T> shootStrategyFactory;
    protected ShootStrategy<T> shootStrategy;

    public AbstractAircraft(float locationX, float locationY, float speedX, float speedY, int hp, int power, AbstractShootStrategyFactory<T> strategyFactory, int shootNum) {
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

    public int getMaxHp() {
        return maxHp;
    }

    /**
     * 飞机射击方法
     *
     * @return 返回射出的子弹列表（可为空）
     */
    public List<T> shoot() {
        return this.shootStrategy.shoot(this.locationX, this.locationY, this.speedY, this.power);
    }

    public DyingAnimation getAnimation() {
        return new DyingAnimation(this);
    }
}


