package net.imshit.element.aircraft.enemy.factory;

import net.imshit.Config;
import net.imshit.element.aircraft.enemy.BossEnemy;
import net.imshit.util.resource.ImageManager;
import net.imshit.util.generate.prop.AbstractPropGenerateStrategy;

/**
 * @author Jim
 */
public class BossEnemyFactory extends AbstractEnemyFactory {
    private final AbstractPropGenerateStrategy propGenerateStrategy;

    public BossEnemyFactory(AbstractPropGenerateStrategy propGenerateStrategy) {
        this.propGenerateStrategy = propGenerateStrategy;
    }

    @Override
    public BossEnemy createEnemy(int hp, int power, float speed) {
        float locationX = (float) Math.random() * (Config.WINDOW_WIDTH - ImageManager.get(BossEnemy.class).getWidth());
        float speedX = speed * (Math.random() < 0.5 ? -1 : 1);
        return new BossEnemy(locationX, 0, speedX, hp, power, 3, this.propGenerateStrategy);
    }
}
