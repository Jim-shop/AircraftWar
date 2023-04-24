package net.imshit.logic.game.generate;

import net.imshit.element.aircraft.enemy.AbstractEnemy;
import net.imshit.element.aircraft.enemy.factory.AbstractEnemyFactory;
import net.imshit.element.aircraft.enemy.factory.BossEnemyFactory;
import net.imshit.element.aircraft.enemy.factory.EliteEnemyFactory;
import net.imshit.element.aircraft.enemy.factory.MobEnemyFactory;

import java.util.List;

/**
 * 简单难度的敌机生成策略
 *
 * @author Jim
 */
public class MediumGenerateStrategy extends AbstractGenerateStrategy {
    private final AbstractEnemyFactory mobFactory = new MobEnemyFactory();
    private final AbstractEnemyFactory eliteFactory = new EliteEnemyFactory();
    private final AbstractEnemyFactory bossFactory = new BossEnemyFactory();

    public MediumGenerateStrategy() {
        this.mobProbability = 0.8;
        this.enemyMaxNumber = 5;
        this.bossScoreThreshold = 200;
        this.bossProbability = 0.05;
        this.generateInterval = 600;
    }

    @Override
    public List<AbstractEnemy> generateEnemy(int currentEnemyNum) {
        if (currentEnemyNum < this.enemyMaxNumber) {
            if (Math.random() < this.mobProbability) {
                return List.of(this.mobFactory.createEnemy());
            } else {
                return List.of(this.eliteFactory.createEnemy());
            }
        } else {
            return List.of();
        }
    }

    @Override
    public AbstractEnemy generateBoss(AbstractEnemy currentBoss, int score) {
        if (score > this.bossScoreThreshold && currentBoss == null && Math.random() < this.bossProbability) {
            return bossFactory.createEnemy();
        } else {
            return currentBoss;
        }
    }
}
