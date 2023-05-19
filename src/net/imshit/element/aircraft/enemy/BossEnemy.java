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

import net.imshit.Config;
import net.imshit.element.prop.AbstractProp;
import net.imshit.util.generate.prop.AbstractPropGenerateStrategy;
import net.imshit.util.listener.Event;

import java.util.List;
import java.util.Objects;

/**
 * Boss敌机
 * 可散射射击
 *
 * @author jim
 */
public class BossEnemy extends AbstractEnemy {

    private final AbstractPropGenerateStrategy propGenerateStrategy;

    public BossEnemy(float locationX, float locationY, float speedX, int hp, int power, int shootNum, AbstractPropGenerateStrategy propGenerateStrategy) {
        super(locationX, locationY, speedX, 0, hp, power, shootNum);
        this.propGenerateStrategy = propGenerateStrategy;
    }

    @Override
    public void forward() {
        locationX += speedX * Config.REFRESH_INTERVAL;
        if (locationX < 0 || locationX >= Config.WINDOW_WIDTH) {
            speedX = -speedX;
        }
    }

    @Override
    public List<AbstractProp> prop() {
        return List.of(
                propGenerateStrategy.createPropNotNull(this.locationX - 20, this.locationY - 10),
                propGenerateStrategy.createPropNotNull(this.locationX, this.locationY),
                propGenerateStrategy.createPropNotNull(this.locationX + 20, this.locationY - 10)
        );
    }

    @Override
    public int getCredits() {
        return 200;
    }

    @Override
    public void notify(Event e) {
        if (Objects.requireNonNull(e) == Event.BOMB_ACTIVATE) {
            this.hp /= 2;
        }
    }
}
