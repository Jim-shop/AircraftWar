/*
 * Copyright (c) 2023 Jim-shop
 * AircraftWar is licensed under Mulan PubL v2.
 * You can use this software according to the terms and conditions of the Mulan PubL v2.
 * You may obtain a copy of Mulan PubL v2 at:
 *          http://license.coscl.org.cn/MulanPubL-2.0
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PubL v2 for more details.
 */

package net.imshit.util.resource;


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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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


    static {
        try {
            ICON = loadImage("icon/icon.png");
            BACKGROUND_IMAGE_EASY = loadImage("image/bg.jpg");
            BACKGROUND_IMAGE_MEDIUM = loadImage("image/bg2.jpg");
            BACKGROUND_IMAGE_HARD = loadImage("image/bg3.jpg");

            CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), loadImage("image/hero.png"));
            CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), loadImage("image/bullet_hero.png"));
            CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), loadImage("image/bullet_enemy.png"));
            CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), loadImage("image/mob.png"));
            CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), loadImage("image/elite.png"));
            CLASSNAME_IMAGE_MAP.put(BossEnemy.class.getName(), loadImage("image/boss.png"));
            CLASSNAME_IMAGE_MAP.put(BloodProp.class.getName(), loadImage("image/prop_blood.png"));
            CLASSNAME_IMAGE_MAP.put(BombProp.class.getName(), loadImage("image/prop_bomb.png"));
            CLASSNAME_IMAGE_MAP.put(BulletProp.class.getName(), loadImage("image/prop_bullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static BufferedImage loadImage(String path) throws IOException {
        return ImageIO.read(Objects.requireNonNull(ImageManager.class.getClassLoader().getResourceAsStream(path)));
    }

    public static BufferedImage get(String className) {
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    public static BufferedImage get(Object obj) {
        if (obj == null) {
            return null;
        }
        return get(obj.getClass());
    }

    public static BufferedImage get(Class<?> className) {
        return get(className.getName());
    }

}
