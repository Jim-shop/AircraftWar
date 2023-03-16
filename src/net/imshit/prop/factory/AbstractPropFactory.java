package net.imshit.prop.factory;

import net.imshit.prop.AbstractProp;

import java.util.List;

abstract public class AbstractPropFactory {
    protected final int speedX = 0;
    protected final int speedY = 3;

    abstract public AbstractProp createProp(int locationX, int locationY);
}
