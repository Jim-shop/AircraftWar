package net.imshit.element.aircraft.enemy.factory;

import net.imshit.element.aircraft.enemy.AbstractEnemy;

/**
 * @author Jim
 */
public abstract class AbstractEnemyFactory {
    /**
     * 敌机工厂的获取敌机方法
     *
     * @param hp    生成的敌机生命值
     * @param power 生成的敌机攻击力
     * @param speed 生成的敌机的速度
     * @return 返回生成的敌机实例
     */
    abstract public AbstractEnemy createEnemy(int hp, int power, float speed);
}
