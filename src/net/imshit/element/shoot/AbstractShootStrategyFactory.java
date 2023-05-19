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

package net.imshit.element.shoot;

import net.imshit.element.bullet.AbstractBullet;

/**
 * 发射策略的抽象工厂
 *
 * @author Jim
 */
public abstract class AbstractShootStrategyFactory<T extends AbstractBullet> {
    /**
     * 根据发射量大小获取对应的发射策略
     *
     * @param shootNum 单次子弹发射量
     * @return 发射策略
     */
    abstract public ShootStrategy<T> getStrategy(int shootNum);
}
