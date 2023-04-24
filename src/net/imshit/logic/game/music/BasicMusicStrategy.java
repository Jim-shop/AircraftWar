package net.imshit.logic.game.music;

import net.imshit.io.music.AudioThread;
import net.imshit.io.resource.AudioManager;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 有音效
 *
 * @author Jim
 */
public class BasicMusicStrategy extends AbstractMusicStrategy {

    private AudioThread bgmThread;
    private final ExecutorService pool = new ThreadPoolExecutor(32, 64, 20, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(512), new BasicThreadFactory.Builder().namingPattern("music-thread-%d").daemon(true).build());

    @Override
    public void setBgm(BgmType bgmType) {
        if (bgmThread != null) {
            bgmThread.interrupt();
        }
        switch (bgmType) {
            case NONE -> bgmThread = null;
            case NORMAL -> {
                bgmThread = new AudioThread(AudioManager.BGM, true);
                pool.execute(bgmThread);
            }
            case BOSS -> {
                bgmThread = new AudioThread(AudioManager.BGM_BOSS, true);
                pool.execute(bgmThread);
            }
            default -> { // 已经列全了，不可能执行到这里
            }
        }
    }

    @Override
    public void playBombExplosion() {
        pool.execute(new AudioThread(AudioManager.BOMB_EXPLOSION, false));
    }

    @Override
    public void playBullet() {
        pool.execute(new AudioThread(AudioManager.BULLET, false));
    }

    @Override
    public void playBulletHit() {
        pool.execute(new AudioThread(AudioManager.BULLET_HIT, false));
    }

    @Override
    public void playGetSupply() {
        pool.execute(new AudioThread(AudioManager.GET_SUPPLY, false));
    }

    @Override
    public void playGameOver() {
        if (bgmThread != null) {
            bgmThread.interrupt();
        }
        pool.execute(new AudioThread(AudioManager.GAME_OVER, false));
    }
}
