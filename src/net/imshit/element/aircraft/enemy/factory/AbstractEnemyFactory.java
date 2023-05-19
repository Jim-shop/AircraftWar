/*
 * Copyright (c) 2023 Jim-shop
 * AircraftWar is licensed under Mulan PubL v2.
 * You can use this software according to the terms and conditions of the Mulan PubL v2.
 * You may obtain a copy of Mulan PubL v2 at:
 *          http://license.coscl.org.cn/MulanPubL-2.0
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PubL v2 for more details.
 */

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
