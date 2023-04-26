package net.imshit.element.aircraft.enemy;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractEnemy {

    public MobEnemy(float locationX, float locationY, float speedX, float speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp, 0, 0);
    }

    @Override
    public int getCredits() {
        return 60;
    }
}
