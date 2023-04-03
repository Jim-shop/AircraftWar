package net.imshit.shoot.hero;

import net.imshit.shoot.ShootStrategy;
import net.imshit.shoot.ShootStrategyFactory;

public class HeroShootStrategyFactory extends ShootStrategyFactory {
    @Override
    public ShootStrategy getStrategy(int shootNum) {
        return switch (shootNum) {
            case 1 -> new HeroDirectShootStrategy();
            case 3 -> new HeroScatterShootStrategy();
            default -> new HeroDirectShootStrategy();
        };
    }
}
