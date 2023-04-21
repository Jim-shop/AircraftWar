package net.imshit.element.prop;

import net.imshit.Config;
import net.imshit.element.aircraft.hero.HeroAircraft;
import net.imshit.element.basic.AbstractFlyingObject;

/**
 * @author Jim
 */
public abstract class AbstractProp extends AbstractFlyingObject {

    public AbstractProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Config.WINDOW_HEIGHT) {
            vanish();
        }
    }

    /**
     * 对英雄机使用道具
     *
     * @param hero 英雄机实例
     */
    public abstract void use(HeroAircraft hero);

}
