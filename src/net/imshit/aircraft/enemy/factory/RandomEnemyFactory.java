package net.imshit.aircraft.enemy.factory;

import net.imshit.aircraft.enemy.AbstractEnemy;

public class RandomEnemyFactory extends AbstractEnemyFactory {
    private final MobEnemyFactory mobFactory = new MobEnemyFactory();
    private final EliteEnemyFactory eliteFactory = new EliteEnemyFactory();

    /**
     * 生成Mob类型的概率
     */
    private final double mobProb = 0.8;

    @Override
    public AbstractEnemy createEnemy() {
        if (Math.random() < mobProb) {
            return mobFactory.createEnemy();
        } else {
            return eliteFactory.createEnemy();
        }
    }
}
