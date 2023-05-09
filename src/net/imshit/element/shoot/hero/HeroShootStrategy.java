package net.imshit.element.shoot.hero;

import net.imshit.element.bullet.HeroBullet;
import net.imshit.element.shoot.ShootStrategy;

import java.util.List;

/**
 * 英雄机的射击策略捏
 *
 * @author Jim
 */
public interface HeroShootStrategy extends ShootStrategy<HeroBullet> {
    /**
     * 英雄机射击接口
     * @param x      飞机水平位置
     * @param y      飞机竖直位置
     * @param speedY 飞机竖直速度
     * @param power  飞机攻击力
     * @return 英雄机子弹列表
     */
    @Override
    List<HeroBullet> shoot(float x, float y, float speedY, int power);
}
