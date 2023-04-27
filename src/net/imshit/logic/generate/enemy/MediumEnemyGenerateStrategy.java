package net.imshit.logic.generate.enemy;

import net.imshit.element.aircraft.enemy.AbstractEnemy;
import net.imshit.element.aircraft.enemy.factory.AbstractEnemyFactory;
import net.imshit.element.aircraft.enemy.factory.BossEnemyFactory;
import net.imshit.element.aircraft.enemy.factory.EliteEnemyFactory;
import net.imshit.element.aircraft.enemy.factory.MobEnemyFactory;
import net.imshit.logic.generate.prop.AbstractPropGenerateStrategy;
import net.imshit.logic.generate.prop.MediumPropGenerateStrategy;

import java.util.List;

/**
 * 简单难度的敌机生成策略
 *
 * @author Jim
 */
public class MediumEnemyGenerateStrategy extends AbstractEnemyGenerateStrategy {
    AbstractPropGenerateStrategy propGenerateStrategy = new MediumPropGenerateStrategy();
    private final AbstractEnemyFactory mobFactory = new MobEnemyFactory();
    private final AbstractEnemyFactory eliteFactory = new EliteEnemyFactory(this.propGenerateStrategy);
    private final AbstractEnemyFactory bossFactory = new BossEnemyFactory(this.propGenerateStrategy);

    public MediumEnemyGenerateStrategy() {
        this.mobProbability = 0.7;
        this.enemyMaxNumber = 8;
        this.bossScoreThreshold = 1000;
        this.enemySummonInterval = 600;
        this.enemyShootInterval = 500;
        this.heroShootInterval = 200;
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
        return bossFactory.createEnemy(300, 30);
    }
}
