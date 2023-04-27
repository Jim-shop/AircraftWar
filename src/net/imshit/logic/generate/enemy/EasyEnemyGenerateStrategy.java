package net.imshit.logic.generate.enemy;

import net.imshit.element.aircraft.enemy.AbstractEnemy;
import net.imshit.element.aircraft.enemy.factory.AbstractEnemyFactory;
import net.imshit.element.aircraft.enemy.factory.EliteEnemyFactory;
import net.imshit.element.aircraft.enemy.factory.MobEnemyFactory;
import net.imshit.logic.generate.prop.AbstractPropGenerateStrategy;
import net.imshit.logic.generate.prop.EasyPropGenerateStrategy;

import java.util.List;

/**
 * 简单难度的敌机生成策略
 *
 * @author Jim
 */
public class EasyEnemyGenerateStrategy extends AbstractEnemyGenerateStrategy {
    AbstractPropGenerateStrategy propGenerateStrategy = new EasyPropGenerateStrategy();
    private final AbstractEnemyFactory mobFactory = new MobEnemyFactory();
    private final AbstractEnemyFactory eliteFactory = new EliteEnemyFactory(this.propGenerateStrategy);

    public EasyEnemyGenerateStrategy() {
        this.mobProbability = 0.8;
        this.enemyMaxNumber = 5;
        this.bossScoreThreshold = 0;
        this.enemySummonInterval = 800;
        this.enemyShootInterval = 500;
        this.heroShootInterval = 100;
    }

    @Override
    public List<AbstractEnemy> generateEnemy(int currentEnemyNum) {
        if (currentEnemyNum < this.enemyMaxNumber) {
            if (Math.random() < this.mobProbability) {
                return List.of(this.mobFactory.createEnemy(30, 0));
            } else {
                return List.of(this.eliteFactory.createEnemy(60, 30));
            }
        } else {
            return List.of();
        }
    }

    @Override
    public AbstractEnemy generateBoss() {
        return null;
    }

    @Override
    public boolean isTimeToGenerateBoss(AbstractEnemy currentBoss, int score) {
        return false;
    }
}
