package net.imshit.element.shoot.enemy;

import net.imshit.element.bullet.AbstractBullet;
import net.imshit.element.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 敌机散射策略
 *
 * @author Jim
 */
public class EnemyScatterShootStrategy implements EnemyShootStrategy {
    @Override
    public List<AbstractBullet> shoot(float x, float y, float speedY, int power) {
        List<AbstractBullet> res = new LinkedList<>();
        int direction = 1;
        int shootNum = 3;
        float bulletY = y + direction * 2;
        float bulletCenterSpeedX = 0;
        float bulletCenterSpeedY = speedY + direction * 0.1f;
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            res.add(new EnemyBullet(x + (i * 2 - shootNum + 1) * 10, bulletY, bulletCenterSpeedX + (i * 2 - shootNum + 1) * 0.01f, bulletCenterSpeedY, power));
        }
        return res;
    }
}
