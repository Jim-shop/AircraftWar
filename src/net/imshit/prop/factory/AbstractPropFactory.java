package net.imshit.prop.factory;

import net.imshit.prop.AbstractProp;

/**
 * @author Jim
 */
abstract public class AbstractPropFactory {
    protected final int speedX = 0;
    protected final int speedY = 3;

    /**
     * 道具工厂创造道具
     *
     * @param locationX 道具掉落水平位置
     * @param locationY 道具掉落垂直位置
     * @return 道具实例
     */
    abstract public AbstractProp createProp(int locationX, int locationY);
}
