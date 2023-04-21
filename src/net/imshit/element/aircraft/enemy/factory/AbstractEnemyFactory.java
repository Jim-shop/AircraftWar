package net.imshit.element.aircraft.enemy.factory;

import net.imshit.element.aircraft.enemy.AbstractEnemy;

/**
 * @author Jim
 */
public abstract class AbstractEnemyFactory {
    /**
     * 敌机工厂的获取敌机方法
     *
     * @return 返回生成的敌机实例
     */
    abstract public AbstractEnemy createEnemy();
}
