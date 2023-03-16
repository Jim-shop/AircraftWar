package net.imshit.aircraft.enemy.factory;

import net.imshit.aircraft.enemy.EliteEnemy;
import net.imshit.application.ImageManager;
import net.imshit.application.Main;

public class EliteEnemyFactory extends AbstractEnemyFactory {
    @Override
    public EliteEnemy createEnemy() {
        int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth()));
        int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
        int speedX = 5;
        int speedY = 5;
        int hp = 60;
        return new EliteEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
