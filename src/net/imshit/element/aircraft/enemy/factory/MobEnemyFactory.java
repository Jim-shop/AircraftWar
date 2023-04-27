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
    public MobEnemy createEnemy(int hp, int power, float speed) {
        float locationX = (float) (Math.random() * (Config.WINDOW_WIDTH - ImageManager.get(MobEnemy.class).getWidth()));
        return new MobEnemy(locationX, 0, 0, speed, hp);
    }
}
