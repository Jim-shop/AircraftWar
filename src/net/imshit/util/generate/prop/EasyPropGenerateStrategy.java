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

/**
 * 随机道具工厂
 *
 * @author Jim
 */
public class EasyPropGenerateStrategy extends AbstractPropGenerateStrategy {
    public EasyPropGenerateStrategy() {
        this.bloodProbability = 0.3;
        this.bombProbability = 0.3;
        this.bulletProbability = 0.3;
    }
}
