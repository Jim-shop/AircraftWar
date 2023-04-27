package net.imshit.element.aircraft.enemy.factory;

import net.imshit.Config;
import net.imshit.element.aircraft.enemy.BossEnemy;
import net.imshit.io.resource.ImageManager;
import net.imshit.logic.generate.prop.AbstractPropGenerateStrategy;

/**
 * @author Jim
 */
public class BossEnemyFactory extends AbstractEnemyFactory {
    private final AbstractPropGenerateStrategy propGenerateStrategy;

    public BossEnemyFactory(AbstractPropGenerateStrategy propGenerateStrategy) {
        this.propGenerateStrategy = propGenerateStrategy;
    }

    @Override
    public BossEnemy createEnemy(int hp, int power) {
        float locationX = (float) Math.random() * (Config.WINDOW_WIDTH - ImageManager.get(BossEnemy.class).getWidth());
        float locationY = 0;
        float speedX = 0.05f * (Math.random() < 0.5 ? -1 : 1);
        int shootNum = 3;
        return new BossEnemy(locationX, locationY, speedX, hp, power, shootNum, this.propGenerateStrategy);
    }
}
