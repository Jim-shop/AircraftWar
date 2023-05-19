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

import java.util.List;

/**
 * 英雄机直射策略
 *
 * @author Jim
 */
public class HeroDirectShootStrategy implements HeroShootStrategy {
    @Override
    public List<HeroBullet> shoot(float x, float y, float speedY, int power) {
        int direction = -1;
        float bulletY = y + direction * 2;
        float bulletSpeedX = 0;
        float bulletSpeedY = speedY + direction * 0.2f;
        return List.of(new HeroBullet(x, bulletY, bulletSpeedX, bulletSpeedY, power));
    }
}
