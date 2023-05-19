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
import net.imshit.util.generate.prop.HardPropGenerateStrategy;

/**
 * 简单难度的敌机生成策略
 *
 * @author Jim
 */
public class HardEnemyGenerateStrategy extends AbstractEnemyGenerateStrategy {
    public HardEnemyGenerateStrategy() {
        var propStrategy = new HardPropGenerateStrategy();
        this.mobFactory = new MobEnemyFactory();
        this.eliteFactory = new EliteEnemyFactory(propStrategy);
        this.bossFactory = new BossEnemyFactory(propStrategy);
        this.mobProbability = 0.6;
        this.enemyMaxNumber = 10;
        this.bossScoreThreshold = 500;
        this.enemySummonInterval = 500;
        this.enemyShootInterval = 500;
        this.heroShootInterval = 500;
        this.hpIncreaseRate = 0.08;
        this.powerIncreaseRate = 0.08;
        this.speedIncreaseRate = 0.0008;
        this.bossHpIncreaseRate = 0.08;
        this.mobBaseHp = 60;
        this.eliteBaseHp = 120;
        this.bossBaseHp = 500;
        this.eliteBasePower = 60;
        this.bossBasePower = 60;
        this.mobBaseSpeed = 0.20f;
        this.eliteBaseSpeed = 0.15f;
        this.bossBaseSpeed = 0.10f;
    }
}
