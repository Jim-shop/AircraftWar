package net.imshit.shoot;

import java.util.InvalidPropertiesFormatException;

/**
 * 发射策略的抽象工厂
 * @author Jim
 */
public abstract class ShootStrategyFactory {
    /**
     * 根据发射量大小获取对应的发射策略
     * @param shootNum 单次子弹发射量
     * @return 发射策略
     */
    abstract public ShootStrategy getStrategy(int shootNum);
}
