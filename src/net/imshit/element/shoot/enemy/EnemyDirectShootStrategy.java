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

import java.util.List;

/**
 * 敌机直射策略
 *
 * @author Jim
 */
public class EnemyDirectShootStrategy implements EnemyShootStrategy {
    @Override
    public List<EnemyBullet> shoot(float x, float y, float speedY, int power) {
        int direction = 1;
        float bulletY = y + direction * 2;
        float bulletSpeedX = 0;
        float bulletSpeedY = speedY + direction * 0.1f;
        return List.of(new EnemyBullet(x, bulletY, bulletSpeedX, bulletSpeedY, power));
    }
}
