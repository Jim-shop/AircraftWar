package net.imshit.element.aircraft.hero;

import net.imshit.Config;
import net.imshit.element.aircraft.AbstractAircraft;
import net.imshit.element.bullet.AbstractBullet;
import net.imshit.element.bullet.HeroBullet;
import net.imshit.element.shoot.hero.HeroShootStrategyFactory;
import net.imshit.io.resource.ImageManager;

import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 *
 * @author Jim
 */
public class HeroAircraft extends AbstractAircraft<HeroBullet> {

    /**
     * 饿汉式单例模式
     */
    private static final HeroAircraft INSTANCE = new HeroAircraft(Config.WINDOW_WIDTH / 2f, Config.WINDOW_HEIGHT - ImageManager.get(HeroAircraft.class).getHeight(), 1000, 30, 1);

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param hp        初始生命值
     * @param power     英雄机战斗力
     * @param shootNum  英雄机单次发射子弹数
     */
    private HeroAircraft(float locationX, float locationY, int hp, int power, int shootNum) {
        super(locationX, locationY, 0, 0, hp, power, new HeroShootStrategyFactory(), shootNum);
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

    public void reset() {
        this.hp = maxHp;
        this.isValid = true;
        this.locationX = Config.WINDOW_WIDTH / 2f;
        this.locationY = Config.WINDOW_HEIGHT - ImageManager.get(HeroAircraft.class).getHeight();
        this.speedX = 0;
        this.speedY = 0;
        this.setShootNum(1);
    }

    public List<HeroBullet> shoot() {
        return super.shoot();
    }
}
