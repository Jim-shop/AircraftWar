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
        float locationX = (float) Math.random() * (Config.WINDOW_WIDTH - ImageManager.get(BossEnemy.class).getWidth());
        float locationY = 0;
        float speedX = 0.05f * (Math.random() < 0.5 ? -1 : 1);
        int hp = 300;
        int power = 20;
        int shootNum = 3;
        return new BossEnemy(locationX, locationY, speedX, hp, power, shootNum);
    }
}
