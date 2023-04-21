package net.imshit.element.prop;

import net.imshit.element.aircraft.hero.HeroAircraft;
import net.imshit.element.basic.AbstractFlyingObject;

/**
 * @author Jim
 */
public abstract class AbstractProp extends AbstractFlyingObject {

    public AbstractProp(float locationX, float locationY, float speedX, float speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    /**
     * 对英雄机使用道具
     *
     * @param hero 英雄机实例
     */
    public abstract void use(HeroAircraft hero);

}
