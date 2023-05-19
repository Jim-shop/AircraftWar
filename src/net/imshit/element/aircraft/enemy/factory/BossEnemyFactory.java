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

import net.imshit.Config;
import net.imshit.element.aircraft.enemy.BossEnemy;
import net.imshit.util.generate.prop.AbstractPropGenerateStrategy;
import net.imshit.util.resource.ImageManager;

/**
 * @author Jim
 */
public class BossEnemyFactory extends AbstractEnemyFactory {
    private final AbstractPropGenerateStrategy propGenerateStrategy;

    public BossEnemyFactory(AbstractPropGenerateStrategy propGenerateStrategy) {
        this.propGenerateStrategy = propGenerateStrategy;
    }

    @Override
    public BossEnemy createEnemy(int hp, int power, float speed) {
        float locationX = (float) Math.random() * (Config.WINDOW_WIDTH - ImageManager.get(BossEnemy.class).getWidth());
        float speedX = speed * (Math.random() < 0.5 ? -1 : 1);
        return new BossEnemy(locationX, 0, speedX, hp, power, 3, this.propGenerateStrategy);
    }
}
