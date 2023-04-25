package net.imshit.logic.game.music;

import net.imshit.utils.music.AudioThread;
import net.imshit.io.resource.AudioManager;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * 有音效
 *
 * @author Jim
 */
public class BasicMusicStrategy extends AbstractMusicStrategy {

    private final ExecutorService pool = new ThreadPoolExecutor(32, 64, 10, TimeUnit.SECONDS, new SynchronousQueue<>(), new BasicThreadFactory.Builder().namingPattern("music-thread-%d").daemon(true).build());
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
