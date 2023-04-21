package net.imshit.element.shoot.hero;

import net.imshit.element.shoot.AbstractShootStrategyFactory;
import net.imshit.element.shoot.ShootStrategy;

/**
 * 英雄机策略工厂
 * @author Jim
 */
public class HeroShootStrategyFactory extends AbstractShootStrategyFactory {
    @Override
    public ShootStrategy getStrategy(int shootNum) {
        return switch (shootNum) {
            case 1 -> new HeroDirectShootStrategy();
            case 3 -> new HeroScatterShootStrategy();
            default -> new HeroDirectShootStrategy();
        };
    }
}
