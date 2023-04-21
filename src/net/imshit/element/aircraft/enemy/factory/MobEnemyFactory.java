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
        float locationX = (float) (Math.random() * (Config.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()));
        float locationY = 0;
        float speedX = 0;
        float speedY = 0.25f;
        int hp = 30;
        return new MobEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
