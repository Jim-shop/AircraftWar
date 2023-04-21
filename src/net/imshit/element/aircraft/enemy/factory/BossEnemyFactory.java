package net.imshit.element.aircraft.enemy.factory;

import net.imshit.Config;
import net.imshit.element.aircraft.enemy.BossEnemy;
import net.imshit.io.resource.ImageManager;

/**
 * @author Jim
 */
public class BossEnemyFactory extends AbstractEnemyFactory {
    @Override
    public BossEnemy createEnemy() {
        int locationX = (int) (Math.random() * (Config.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth()));
        int locationY = (int) (Config.WINDOW_HEIGHT * 0.05);
        int speedX = 5 * (Math.random() < 0.5 ? -1 : 1);
        int speedY = 0;
        int hp = 300;
        int power = 20;
        return new BossEnemy(locationX, locationY, speedX, speedY, hp, power);
    }
}
