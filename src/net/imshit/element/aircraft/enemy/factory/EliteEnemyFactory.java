package net.imshit.element.aircraft.enemy.factory;

import net.imshit.Config;
import net.imshit.element.aircraft.enemy.EliteEnemy;
import net.imshit.io.resource.ImageManager;
import net.imshit.logic.generate.prop.AbstractPropGenerateStrategy;

/**
 * @author Jim
 */
public class EliteEnemyFactory extends AbstractEnemyFactory {
    private final AbstractPropGenerateStrategy propGenerateStrategy;

    public EliteEnemyFactory(AbstractPropGenerateStrategy propGenerateStrategy) {
        this.propGenerateStrategy = propGenerateStrategy;
    }

    @Override
    public EliteEnemy createEnemy(int hp, int power, float speed) {
        float locationX = (float) (Math.random() * (Config.WINDOW_WIDTH - ImageManager.get(EliteEnemy.class).getWidth()));
        return new EliteEnemy(locationX, 0, 0, speed, hp, power, this.propGenerateStrategy);
    }
}
