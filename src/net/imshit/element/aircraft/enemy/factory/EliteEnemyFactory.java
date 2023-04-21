package net.imshit.element.aircraft.enemy.factory;

import net.imshit.Config;
import net.imshit.element.aircraft.enemy.EliteEnemy;
import net.imshit.io.resource.ImageManager;

/**
 * @author Jim
 */
public class EliteEnemyFactory extends AbstractEnemyFactory {
    @Override
    public EliteEnemy createEnemy() {
        float locationX = (float) (Math.random() * (Config.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth()));
        float locationY = 0;
        float speedX = 0;
        float speedY = 0.1f;
        int hp = 60;
        int power = 20;
        return new EliteEnemy(locationX, locationY, speedX, speedY, hp, power);
    }
}
