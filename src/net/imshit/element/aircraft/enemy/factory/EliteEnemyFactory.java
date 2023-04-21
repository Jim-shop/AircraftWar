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
        int locationX = (int) (Math.random() * (Config.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth()));
        int locationY = (int) (Math.random() * Config.WINDOW_HEIGHT * 0.05);
        int speedX = 0;
        int speedY = 5;
        int hp = 60;
        return new EliteEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
