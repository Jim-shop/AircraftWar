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

import java.net.URL;

/**
 * 集中音频资源的访问
 *
 * @author Jim
 */
public class AudioManager {
    public static final URL BGM;
    public static final URL BGM_BOSS;
    public static final URL BOMB_EXPLOSION;
    public static final URL BULLET;
    public static final URL BULLET_HIT;
    public static final URL GAME_OVER;
    public static final URL GET_SUPPLY;

    static {
        BGM = getUrl("audio/bgm.wav");
        BGM_BOSS = getUrl("audio/bgm_boss.wav");
        BOMB_EXPLOSION = getUrl("audio/bomb_explosion.wav");
        BULLET = getUrl("audio/bullet.wav");
        BULLET_HIT = getUrl("audio/bullet_hit.wav");
        GAME_OVER = getUrl("audio/game_over.wav");
        GET_SUPPLY = getUrl("audio/get_supply.wav");
    }

    private static URL getUrl(String path) {
        return AudioManager.class.getClassLoader().getResource(path);
    }
}
