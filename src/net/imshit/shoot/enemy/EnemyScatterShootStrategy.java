package net.imshit.shoot.enemy;

import net.imshit.bullet.AbstractBullet;
import net.imshit.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 敌机散射策略
 * @author Jim
 */
public class EnemyScatterShootStrategy implements EnemyShootStrategy {
    @Override
    public List<AbstractBullet> shoot(int x, int y, int speedY, int power) {
        List<AbstractBullet> res = new LinkedList<>();
        int direction = 1;
        int shootNum = 3;
        int bulletX = x;
        int bulletY = y + direction * 2;
        int bulletCenterSpeedX = 0;
        int bulletCenterSpeedY = speedY + direction * 10;
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            res.add(new EnemyBullet(bulletX + (i * 2 - shootNum + 1) * 10, bulletY, bulletCenterSpeedX + (i * 2 - shootNum + 1), bulletCenterSpeedY, power));
        }
        return res;
    }
}
