package net.imshit.element.aircraft.enemy.factory;

import net.imshit.Config;
import net.imshit.element.aircraft.enemy.MobEnemy;
import net.imshit.io.resource.ImageManager;

/**
 * 普通敌机的工厂
 *
 * @author Jim
 */
public class MobEnemyFactory extends AbstractEnemyFactory {
    @Override
    public MobEnemy createEnemy() {
        int locationX = (int) (Math.random() * (Config.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        int locationY = (int) (Math.random() * Config.WINDOW_HEIGHT * 0.05);
        int speedX = 0;
        int speedY = 10;
        int hp = 30;
        return new MobEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
