package net.imshit.aircraft.enemy.factory;

import net.imshit.aircraft.enemy.AbstractEnemy;

/**
 * 随机敌机工厂
 *
 * @author Jim
 */
public class RandomEnemyFactory extends AbstractEnemyFactory {
    private final MobEnemyFactory mobFactory = new MobEnemyFactory();
    private final EliteEnemyFactory eliteFactory = new EliteEnemyFactory();

    public AbstractEnemy createEnemy() {
        var mobProb = 0.8;
        if (Math.random() < mobProb) {
            return mobFactory.createEnemy();
        } else {
            return eliteFactory.createEnemy();
        }
    }
}
