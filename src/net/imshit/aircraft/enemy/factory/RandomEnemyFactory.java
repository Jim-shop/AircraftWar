package net.imshit.aircraft.enemy.factory;

import net.imshit.aircraft.enemy.AbstractEnemy;

public class RandomEnemyFactory extends AbstractEnemyFactory {
    private MobEnemyFactory mobFactory = new MobEnemyFactory();
    private EliteEnemyFactory eliteFactory = new EliteEnemyFactory();

    /**
     * 生成Mob类型的概率
     */
    private double mobThreshold = 0.8;

    @Override
    public AbstractEnemy createEnemy() {
        if (Math.random() < mobThreshold) {
            return mobFactory.createEnemy();
        } else {
            return eliteFactory.createEnemy();
        }
    }
}
