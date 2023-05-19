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

package net.imshit.element.aircraft.enemy;

import net.imshit.element.prop.AbstractProp;
import net.imshit.util.generate.prop.AbstractPropGenerateStrategy;

import java.util.List;

/**
 * 精英敌机
 * 可射击
 *
 * @author jim
 */
public class EliteEnemy extends AbstractEnemy {
    private final AbstractPropGenerateStrategy propGenerateStrategy;

    public EliteEnemy(float locationX, float locationY, float speedX, float speedY, int hp, int power, AbstractPropGenerateStrategy propGenerateStrategy) {
        super(locationX, locationY, speedX, speedY, hp, power, 1);
        this.propGenerateStrategy = propGenerateStrategy;
    }

    @Override
    public List<AbstractProp> prop() {
        var prop = propGenerateStrategy.createProp(this.locationX, this.locationY);
        if (prop != null) {
            return List.of(prop);
        } else {
            return List.of();
        }
    }

    @Override
    public int getCredits() {
        return 60;
    }
}
