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

import net.imshit.element.AbstractFlyingObject;
import net.imshit.gui.GamePanel;

/**
 * @author Jim
 */
public abstract class AbstractProp extends AbstractFlyingObject {

    public AbstractProp(float locationX, float locationY, float speedX, float speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    /**
     * 对英雄机使用道具
     *
     * @param game 英雄机实例
     */
    public abstract void activate(GamePanel game);
}
