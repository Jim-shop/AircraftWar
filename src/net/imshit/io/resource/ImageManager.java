package net.imshit.io.resource;


import net.imshit.element.aircraft.enemy.BossEnemy;
import net.imshit.element.aircraft.enemy.EliteEnemy;
import net.imshit.element.aircraft.enemy.MobEnemy;
import net.imshit.element.aircraft.hero.HeroAircraft;
import net.imshit.element.bullet.EnemyBullet;
import net.imshit.element.bullet.HeroBullet;
import net.imshit.element.prop.BloodProp;
import net.imshit.element.prop.BombProp;
import net.imshit.element.prop.BulletProp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author jim-shop
 */
public class ImageManager {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    private static final Map<String, BufferedImage> CLASSNAME_IMAGE_MAP = new HashMap<>();

    public static BufferedImage ICON;
    public static BufferedImage BACKGROUND_IMAGE_EASY;
    public static BufferedImage BACKGROUND_IMAGE_MEDIUM;
    public static BufferedImage BACKGROUND_IMAGE_HARD;
    public static BufferedImage HERO_IMAGE;
    public static BufferedImage HERO_BULLET_IMAGE;
    public static BufferedImage ENEMY_BULLET_IMAGE;
    public static BufferedImage MOB_ENEMY_IMAGE;
    public static BufferedImage ELITE_ENEMY_IMAGE;
    public static BufferedImage BOSS_ENEMY_IMAGE;
    public static BufferedImage PROP_BLOOD_ENEMY_IMAGE;
    public static BufferedImage PROP_BOMB_ENEMY_IMAGE;
    public static BufferedImage PROP_BULLET_ENEMY_IMAGE;

    static {
        try {
            ICON = ImageIO.read(new FileInputStream("resources/icon/icon.png"));

            BACKGROUND_IMAGE_EASY = ImageIO.read(new FileInputStream("resources/image/bg.jpg"));
            BACKGROUND_IMAGE_MEDIUM = ImageIO.read(new FileInputStream("resources/image/bg2.jpg"));
            BACKGROUND_IMAGE_HARD = ImageIO.read(new FileInputStream("resources/image/bg3.jpg"));
            HERO_IMAGE = ImageIO.read(new FileInputStream("resources/image/hero.png"));
            HERO_BULLET_IMAGE = ImageIO.read(new FileInputStream("resources/image/bullet_hero.png"));
            ENEMY_BULLET_IMAGE = ImageIO.read(new FileInputStream("resources/image/bullet_enemy.png"));
            MOB_ENEMY_IMAGE = ImageIO.read(new FileInputStream("resources/image/mob.png"));
            ELITE_ENEMY_IMAGE = ImageIO.read(new FileInputStream("resources/image/elite.png"));
            BOSS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("resources/image/boss.png"));
            PROP_BLOOD_ENEMY_IMAGE = ImageIO.read(new FileInputStream("resources/image/prop_blood.png"));
            PROP_BOMB_ENEMY_IMAGE = ImageIO.read(new FileInputStream("resources/image/prop_bomb.png"));
            PROP_BULLET_ENEMY_IMAGE = ImageIO.read(new FileInputStream("resources/image/prop_bullet.png"));

            CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), ELITE_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BossEnemy.class.getName(), BOSS_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BloodProp.class.getName(), PROP_BLOOD_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BombProp.class.getName(), PROP_BOMB_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BulletProp.class.getName(), PROP_BULLET_ENEMY_IMAGE);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static BufferedImage get(String className) {
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    public static BufferedImage get(Object obj) {
        if (obj == null) {
            return null;
        }
        return get(obj.getClass().getName());
    }

}
