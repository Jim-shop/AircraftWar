package net.imshit.element.prop;

import net.imshit.gui.GamePanel;
import net.imshit.logic.listener.Event;

/**
 * @author Jim
 */
public class BombProp extends AbstractProp {

    public BombProp(float locationX, float locationY, float speedX, float speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activate(GamePanel game) {
        game.notify(Event.BOMB_ACTIVATE);
    }
}