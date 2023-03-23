package net.imshit.prop;

import net.imshit.aircraft.hero.HeroAircraft;
import net.imshit.application.Main;
import net.imshit.basic.AbstractFlyingObject;

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
        if (locationY >= Main.WINDOW_HEIGHT) {
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
