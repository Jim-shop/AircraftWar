package net.imshit.element.shoot.enemy;

import net.imshit.element.bullet.EnemyBullet;
import net.imshit.element.shoot.AbstractShootableFactory;
import net.imshit.element.shoot.Shootable;

/**
 * 敌机策略工厂
 *
 * @author Jim
 */
public class EnemyShootableFactory extends AbstractShootableFactory<EnemyBullet> {
    @Override
    public Shootable<EnemyBullet> getStrategy(int shootNum) {
        return switch (shootNum) {
            case 1 -> new EnemyDirectShootable();
            case 3 -> new EnemyScatterShootable();
            default -> new EnemyNoShootable();
        };
    }
}
