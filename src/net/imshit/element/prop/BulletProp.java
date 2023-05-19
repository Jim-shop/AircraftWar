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

package net.imshit.element.prop;

import net.imshit.gui.GamePanel;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jim
 */
public class BulletProp extends AbstractProp {

    private static final AtomicInteger USED_COUNT = new AtomicInteger(0);

    public BulletProp(float locationX, float locationY, float speedX, float speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activate(GamePanel game) {
        USED_COUNT.getAndIncrement();
        var heroAircraft = game.getHeroAircraft();
        heroAircraft.setShootNum(3);
        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ignored) {
            }
            if (USED_COUNT.decrementAndGet() == 0) {
                heroAircraft.setShootNum(1);
            }
        }).start();
    }
}