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
import net.imshit.element.aircraft.enemy.EliteEnemy;
import net.imshit.util.generate.prop.AbstractPropGenerateStrategy;
import net.imshit.util.resource.ImageManager;

/**
 * @author Jim
 */
public class EliteEnemyFactory extends AbstractEnemyFactory {
    private final AbstractPropGenerateStrategy propGenerateStrategy;

    public EliteEnemyFactory(AbstractPropGenerateStrategy propGenerateStrategy) {
        this.propGenerateStrategy = propGenerateStrategy;
    }

    @Override
    public EliteEnemy createEnemy(int hp, int power, float speed) {
        float locationX = (float) (Math.random() * (Config.WINDOW_WIDTH - ImageManager.get(EliteEnemy.class).getWidth()));
        return new EliteEnemy(locationX, 0, 0, speed, hp, power, this.propGenerateStrategy);
    }
}
