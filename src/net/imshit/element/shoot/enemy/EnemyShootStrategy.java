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

package net.imshit.element.shoot.enemy;

import net.imshit.element.bullet.EnemyBullet;
import net.imshit.element.shoot.ShootStrategy;

import java.util.List;

/**
 * 敌机策略
 *
 * @author Jim
 */
public interface EnemyShootStrategy extends ShootStrategy<EnemyBullet> {
    /**
     * 敌机设计接口
     * @param x      飞机水平位置
     * @param y      飞机竖直位置
     * @param speedY 飞机竖直速度
     * @param power  飞机攻击力
     * @return 敌机子弹
     */
    @Override
    List<EnemyBullet> shoot(float x, float y, float speedY, int power);
}
