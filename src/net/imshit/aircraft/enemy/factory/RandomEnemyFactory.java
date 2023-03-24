package net.imshit.aircraft.enemy.factory;

import net.imshit.aircraft.enemy.AbstractEnemy;

/**
 * 随机敌机工厂
 *
 * @author Jim
 */
public class RandomEnemyFactory {
    private final MobEnemyFactory mobFactory = new MobEnemyFactory();
    private final EliteEnemyFactory eliteFactory = new EliteEnemyFactory();
    private final BossEnemyFactory bossFactory = new BossEnemyFactory();

    public AbstractEnemy createEnemy(int score) {
        var bossScoreThreshold = 300;
        var bossProb = 0.05;
        var mobProb = 0.8;
        if (score > bossScoreThreshold && Math.random() < bossProb) {
            return bossFactory.createEnemy();
        } else {
            if (Math.random() < mobProb) {
                return mobFactory.createEnemy();
            } else {
                return eliteFactory.createEnemy();
            }
        }
    }
}
