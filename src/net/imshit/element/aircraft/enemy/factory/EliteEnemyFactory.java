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
    public EliteEnemy createEnemy(int hp, int power) {
        float locationX = (float) (Math.random() * (Config.WINDOW_WIDTH - ImageManager.get(EliteEnemy.class).getWidth()));
        float locationY = 0;
        float speedX = 0;
        float speedY = 0.1f;
        return new EliteEnemy(locationX, locationY, speedX, speedY, hp, power, this.propGenerateStrategy);
    }
}
