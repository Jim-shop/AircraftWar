package net.imshit.element.bullet;

import net.imshit.element.basic.AbstractFlyingObject;

/**
 * 子弹类。
 * 也可以考虑不同类型的子弹
 *
 * @author jim-shop
 */
public abstract class AbstractBullet extends AbstractFlyingObject {

    private final int power;

    public AbstractBullet(float locationX, float locationY, float speedX, float speedY, int power) {
        super(locationX, locationY, speedX, speedY);
        this.power = power;
    }

    @Override
    public void forward() {
        super.forward();

        // 额外判定 y 轴出界
        if (locationY <= 0) {
            vanish();
        }
    }

    public int getPower() {
        return power;
    }
}
