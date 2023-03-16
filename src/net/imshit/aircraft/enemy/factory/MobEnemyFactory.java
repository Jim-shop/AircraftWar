package net.imshit.aircraft.enemy.factory;

import net.imshit.aircraft.enemy.MobEnemy;
import net.imshit.application.ImageManager;
import net.imshit.application.Main;

public class MobEnemyFactory extends AbstractEnemyFactory{
    @Override
    public MobEnemy createEnemy() {
        int locationX = (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        int locationY = (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05);
        int speedX = 0;
        int speedY = 10;
        int hp = 30;
        return new MobEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
