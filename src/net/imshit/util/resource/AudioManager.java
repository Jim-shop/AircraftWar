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
