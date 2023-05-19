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

package net.imshit.element.shoot.hero;

import net.imshit.element.bullet.HeroBullet;
import net.imshit.element.shoot.AbstractShootStrategyFactory;
import net.imshit.element.shoot.ShootStrategy;

/**
 * 英雄机策略工厂
 *
 * @author Jim
 */
public class HeroShootStrategyFactory extends AbstractShootStrategyFactory<HeroBullet> {
    @Override
    public ShootStrategy<HeroBullet> getStrategy(int shootNum) {
        if (shootNum == 3) {
            return new HeroScatterShootStrategy();
        } else {
            return new HeroDirectShootStrategy();
        }
    }
}
