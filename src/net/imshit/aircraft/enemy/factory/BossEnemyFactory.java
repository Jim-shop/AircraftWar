package net.imshit.aircraft.enemy.factory;

import net.imshit.aircraft.enemy.BossEnemy;
import net.imshit.application.ImageManager;
import net.imshit.application.Main;

/**
 * @author Jim
 */
public class BossEnemyFactory extends AbstractEnemyFactory {
    @Override
    public BossEnemy createEnemy() {
        int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth()));
        int locationY = (int) (Main.WINDOW_HEIGHT * 0.05);
        int speedX = 5 * (Math.random() < 0.5 ? -1 : 1);
        int speedY = 0;
        int hp = 300;
        return new BossEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
