package net.imshit.io.resource;

import java.io.File;

/**
 * 集中音频资源的访问
 *
 * @author Jim
 */
public class AudioManager {
    public static File BGM;
    public static File BGM_BOSS;
    public static File BOMB_EXPLOSION;
    public static File BULLET;
    public static File BULLET_HIT;
    public static File GAME_OVER;
    public static File GET_SUPPLY;

    static {
        BGM = getFile("resources/audio/bgm.wav");
        BGM_BOSS = getFile("resources/audio/bgm_boss.wav");
        BOMB_EXPLOSION = getFile("resources/audio/bomb_explosion.wav");
        BULLET = getFile("resources/audio/bullet.wav");
        BULLET_HIT = getFile("resources/audio/bullet_hit.wav");
        GAME_OVER = getFile("resources/audio/game_over.wav");
        GET_SUPPLY = getFile("resources/audio/get_supply.wav");
    }

    private static File getFile(String filePath) {
//        try {
//            return AudioSystem.getAudioInputStream(new File(filePath));
//        } catch (UnsupportedAudioFileException | IOException e) {
//            e.printStackTrace();
//            System.exit(-1);
//        }
//        return null;
        return new File(filePath);
    }
}
