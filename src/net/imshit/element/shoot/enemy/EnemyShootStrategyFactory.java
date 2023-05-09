package net.imshit.element.shoot.enemy;

import net.imshit.element.bullet.EnemyBullet;
import net.imshit.element.shoot.AbstractShootStrategyFactory;
import net.imshit.element.shoot.ShootStrategy;

/**
 * 敌机策略工厂
 *
 * @author Jim
 */
public class EnemyShootStrategyFactory extends AbstractShootStrategyFactory<EnemyBullet> {
    @Override
    public ShootStrategy<EnemyBullet> getStrategy(int shootNum) {
        return switch (shootNum) {
            case 1 -> new EnemyDirectShootStrategy();
            case 3 -> new EnemyScatterShootStrategy();
            default -> new EnemyNoShootStrategy();
        };
    }
}
