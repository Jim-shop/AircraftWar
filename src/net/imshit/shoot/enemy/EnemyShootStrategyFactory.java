package net.imshit.shoot.enemy;

import net.imshit.shoot.ShootStrategy;
import net.imshit.shoot.ShootStrategyFactory;
import net.imshit.shoot.hero.HeroDirectShootStrategy;
import net.imshit.shoot.hero.HeroScatterShootStrategy;

public class EnemyShootStrategyFactory extends ShootStrategyFactory {
    @Override
    public ShootStrategy getStrategy(int shootNum) {
        ShootStrategy result;
        switch (shootNum) {
            case 0 -> {
                result = new EnemyNoShootStrategy();
            }
            case 1 -> {
                result = new EnemyDirectShootStrategy();
            }
            case 3 -> {
                result = new EnemyScatterShootStrategy();
            }
            default -> {
                result = new EnemyNoShootStrategy();
            }
        }
        return result;
    }
}
