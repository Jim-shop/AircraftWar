package net.imshit.logic.game.music;

import net.imshit.io.music.AudioThread;
import net.imshit.io.resource.AudioManager;

/**
 * 有音效
 *
 * @author Jim
 */
public class BasicMusicStrategy extends AbstractMusicStrategy {

    private AudioThread bgmThread;

    @Override
    public void setBgm(BgmType bgmType) {
        if (bgmThread != null) {
            bgmThread.interrupt();
        }
        switch (bgmType) {
            case NONE -> bgmThread = null;
            case NORMAL -> {
                bgmThread = new AudioThread(AudioManager.BGM, true);
                bgmThread.start();
            }
            case BOSS -> {
                bgmThread = new AudioThread(AudioManager.BGM_BOSS, true);
                bgmThread.start();
            }
            default -> { // 已经列全了，不可能执行到这里
            }
        }
    }

    @Override
    public void playBombExplosion() {
        new AudioThread(AudioManager.BOMB_EXPLOSION, false).start();
    }

    @Override
    public void playBullet() {
        new AudioThread(AudioManager.BULLET, false).start();
    }

    @Override
    public void playBulletHit() {
        new AudioThread(AudioManager.BULLET_HIT, false).start();
    }

    @Override
    public void playGetSupply() {
        new AudioThread(AudioManager.GET_SUPPLY, false).start();
    }

    @Override
    public void playGameOver() {
        if (bgmThread != null) {
            bgmThread.interrupt();
        }
        new AudioThread(AudioManager.GAME_OVER, false).start();
    }
}
