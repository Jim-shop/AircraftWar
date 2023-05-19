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

package net.imshit.util.generate.prop;

import net.imshit.element.prop.AbstractProp;
import net.imshit.element.prop.factory.AbstractPropFactory;
import net.imshit.element.prop.factory.BloodPropFactory;
import net.imshit.element.prop.factory.BombPropFactory;
import net.imshit.element.prop.factory.BulletPropFactory;

/**
 * 随机道具工厂
 *
 * @author Jim
 */
public abstract class AbstractPropGenerateStrategy extends AbstractPropFactory {
    protected double bloodProbability;
    protected double bombProbability;
    protected double bulletProbability;
    protected final BloodPropFactory bloodFactory = new BloodPropFactory();
    protected final BombPropFactory bombFactory = new BombPropFactory();
    protected final BulletPropFactory bulletFactory = new BulletPropFactory();

    @Override
    public AbstractProp createProp(float locationX, float locationY) {
        var rand = Math.random();
        if (rand < bloodProbability) {
            return bloodFactory.createProp(locationX, locationY);
        } else if (rand < bloodProbability + bombProbability) {
            return bombFactory.createProp(locationX, locationY);
        } else if (rand < bloodProbability + bombProbability + bulletProbability) {
            return bulletFactory.createProp(locationX, locationY);
        } else {
            return null;
        }
    }

    public AbstractProp createPropNotNull(float locationX, float locationY) {
        var rand = Math.random() / (bloodProbability + bombProbability + bulletProbability);
        if (rand < bloodProbability) {
            return bloodFactory.createProp(locationX, locationY);
        } else if (rand < bloodProbability + bombProbability) {
            return bombFactory.createProp(locationX, locationY);
        } else {
            return bulletFactory.createProp(locationX, locationY);
        }
    }
}