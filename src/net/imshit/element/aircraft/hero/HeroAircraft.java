package net.imshit.element.aircraft.hero;

import net.imshit.Config;
import net.imshit.element.aircraft.AbstractAircraft;
import net.imshit.element.shoot.hero.HeroShootStrategyFactory;
import net.imshit.io.resource.ImageManager;

/**
 * 英雄飞机，游戏玩家操控
 *
 * @author Jim
 */
public class HeroAircraft extends AbstractAircraft {

    /**
     * 饿汉式单例模式
     */
    private static final HeroAircraft INSTANCE = new HeroAircraft(Config.WINDOW_WIDTH / 2, Config.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight(), 0, 0, 1000, 30, 1);

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp        初始生命值
     * @param power     英雄机战斗力
     * @param shootNum  英雄机单次发射子弹数
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp, int power, int shootNum) {
        super(locationX, locationY, speedX, speedY, hp, power, new HeroShootStrategyFactory(), shootNum);
    }

    public static HeroAircraft getInstance() {
        return INSTANCE;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    public void increaseHp(int increment) {
        if (increment > 0) {
            hp += increment;
            if (hp > maxHp || hp < 0) {
                hp = maxHp;
            }
        }
    }

}
