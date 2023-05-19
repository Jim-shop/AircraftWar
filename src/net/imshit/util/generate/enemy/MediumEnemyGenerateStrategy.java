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

package net.imshit.util.generate.enemy;

import net.imshit.element.aircraft.enemy.factory.BossEnemyFactory;
import net.imshit.element.aircraft.enemy.factory.EliteEnemyFactory;
import net.imshit.element.aircraft.enemy.factory.MobEnemyFactory;
import net.imshit.util.generate.prop.MediumPropGenerateStrategy;

/**
 * 简单难度的敌机生成策略
 *
 * @author Jim
 */
public class MediumEnemyGenerateStrategy extends AbstractEnemyGenerateStrategy {

    public MediumEnemyGenerateStrategy() {
        var propStrategy = new MediumPropGenerateStrategy();
        this.mobFactory = new MobEnemyFactory();
        this.eliteFactory = new EliteEnemyFactory(propStrategy);
        this.bossFactory = new BossEnemyFactory(propStrategy);
        this.mobProbability = 0.7;
        this.enemyMaxNumber = 8;
        this.bossScoreThreshold = 1000;
        this.enemySummonInterval = 600;
        this.enemyShootInterval = 500;
        this.heroShootInterval = 200;
        this.hpIncreaseRate = 0.05;
        this.powerIncreaseRate = 0.05;
        this.speedIncreaseRate = 0.0005;
        this.bossHpIncreaseRate = 0;
        this.mobBaseHp = 60;
        this.eliteBaseHp = 90;
        this.bossBaseHp = 200;
        this.eliteBasePower = 30;
        this.bossBasePower = 30;
        this.mobBaseSpeed = 0.15f;
        this.eliteBaseSpeed = 0.10f;
        this.bossBaseSpeed = 0.05f;
    }
}
