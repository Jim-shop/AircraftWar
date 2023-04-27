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
    public MobEnemy createEnemy(int hp, int power) {
        float locationX = (float) (Math.random() * (Config.WINDOW_WIDTH - ImageManager.get(MobEnemy.class).getWidth()));
        float locationY = 0;
        float speedX = 0;
        float speedY = 0.25f;
        return new MobEnemy(locationX, locationY, speedX, speedY, hp);
    }
}
