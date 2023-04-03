package net.imshit.shoot.hero;

import net.imshit.shoot.ShootStrategy;
import net.imshit.shoot.ShootStrategyFactory;

public class HeroShootStrategyFactory extends ShootStrategyFactory {
    @Override
    public ShootStrategy getStrategy(int shootNum) {
        ShootStrategy result;
        switch (shootNum) {
            case 1 -> {
                result = new HeroDirectShootStrategy();
            }
            case 3 -> {
                result = new HeroScatterShootStrategy();
            }
            default -> {
                result = new HeroDirectShootStrategy();
            }
        }
        return result;
    }
}
