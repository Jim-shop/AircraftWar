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
import net.imshit.element.shoot.AbstractShootStrategyFactory;
import net.imshit.element.shoot.ShootStrategy;

/**
 * 敌机策略工厂
 *
 * @author Jim
 */
public class EnemyShootStrategyFactory extends AbstractShootStrategyFactory<EnemyBullet> {
    @Override
    public ShootStrategy<EnemyBullet> getStrategy(int shootNum) {
        return switch (shootNum) {
            case 1 -> new EnemyDirectShootStrategy();
            case 3 -> new EnemyScatterShootStrategy();
            default -> new EnemyNoShootStrategy();
        };
    }
}
