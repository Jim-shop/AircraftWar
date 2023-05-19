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

package net.imshit.element.bullet;

import net.imshit.element.AbstractFlyingObject;

/**
 * 子弹类。
 * 也可以考虑不同类型的子弹
 *
 * @author jim-shop
 */
public abstract class AbstractBullet extends AbstractFlyingObject {

    private final int power;

    public AbstractBullet(float locationX, float locationY, float speedX, float speedY, int power) {
        super(locationX, locationY, speedX, speedY);
        this.power = power;
    }

    @Override
    public void forward() {
        super.forward();

        // 额外判定 y 轴出界
        if (locationY <= 0) {
            vanish();
        }
    }

    public int getPower() {
        return power;
    }
}
