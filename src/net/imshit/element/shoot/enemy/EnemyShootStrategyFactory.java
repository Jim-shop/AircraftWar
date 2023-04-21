package net.imshit.element.shoot.enemy;

import net.imshit.element.shoot.AbstractShootStrategyFactory;
import net.imshit.element.shoot.ShootStrategy;

/**
 * 敌机策略工厂
 *
 * @author Jim
 */
public class EnemyShootStrategyFactory extends AbstractShootStrategyFactory {
    @Override
    public ShootStrategy getStrategy(int shootNum) {
        return switch (shootNum) {
            case 0 -> new EnemyNoShootStrategy();
            case 1 -> new EnemyDirectShootStrategy();
            case 3 -> new EnemyScatterShootStrategy();
            default -> new EnemyNoShootStrategy();
        };
    }
}
