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

package net.imshit.element.prop.factory;

import net.imshit.element.prop.AbstractProp;

/**
 * @author Jim
 */
abstract public class AbstractPropFactory {
    protected final float speedX = 0;
    protected final float speedY = 0.1f;

    /**
     * 道具工厂创造道具
     *
     * @param locationX 道具掉落水平位置
     * @param locationY 道具掉落垂直位置
     * @return 道具实例
     */
    abstract public AbstractProp createProp(float locationX, float locationY);
}
