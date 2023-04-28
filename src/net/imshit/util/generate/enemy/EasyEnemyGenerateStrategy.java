package net.imshit.util.generate.enemy;

import net.imshit.element.aircraft.enemy.AbstractEnemy;
import net.imshit.element.aircraft.enemy.factory.EliteEnemyFactory;
import net.imshit.element.aircraft.enemy.factory.MobEnemyFactory;
import net.imshit.util.generate.prop.EasyPropGenerateStrategy;

/**
 * 简单难度的敌机生成策略
 *
 * @author Jim
 */
public class EasyEnemyGenerateStrategy extends AbstractEnemyGenerateStrategy {

    public EasyEnemyGenerateStrategy() {
        var propStrategy = new EasyPropGenerateStrategy();
        this.mobFactory = new MobEnemyFactory();
        this.eliteFactory = new EliteEnemyFactory(propStrategy);
        this.bossFactory = new EliteEnemyFactory(propStrategy);
        this.mobProbability = 0.8;
        this.enemyMaxNumber = 5;
        this.bossScoreThreshold = 0;
        this.enemySummonInterval = 800;
        this.enemyShootInterval = 500;
        this.heroShootInterval = 100;
        this.hpIncreaseRate = 0;
        this.powerIncreaseRate = 0;
        this.speedIncreaseRate = 0;
        this.mobBaseHp = 30;
        this.eliteBaseHp = 60;
        this.eliteBasePower = 30;
        this.mobBaseSpeed = 0.15f;
        this.eliteBaseSpeed = 0.10f;
    }

    @Override
    public AbstractEnemy generateBoss() {
        return null;
    }

    @Override
    public boolean isTimeToGenerateBoss(AbstractEnemy currentBoss) {
        return false;
    }
}
