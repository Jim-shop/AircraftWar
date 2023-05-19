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

import net.imshit.element.aircraft.AbstractAircraft;
import net.imshit.element.bullet.EnemyBullet;
import net.imshit.element.prop.AbstractProp;
import net.imshit.element.shoot.enemy.EnemyShootStrategyFactory;
import net.imshit.util.listener.EnemyListener;
import net.imshit.util.listener.Event;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Jim
 */
public abstract class AbstractEnemy extends AbstractAircraft<EnemyBullet> implements EnemyListener {

    public AbstractEnemy(float locationX, float locationY, float speedX, float speedY, int hp, int power, int shootNum) {
        super(locationX, locationY, speedX, speedY, hp, power, new EnemyShootStrategyFactory(), shootNum);
    }

    public List<AbstractProp> prop() {
        return new LinkedList<>();
    }

    public int getCredits() {
        return 0;
    }

    @Override
    public void notify(Event e) {
        if (Objects.requireNonNull(e) == Event.BOMB_ACTIVATE) {
            this.vanish();
        }
    }
}
