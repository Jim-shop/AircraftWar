package net.imshit.element.prop;

import net.imshit.element.AbstractFlyingObject;
import net.imshit.gui.GamePanel;

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
     * @param game 英雄机实例
     */
    public abstract void activate(GamePanel game);
}
