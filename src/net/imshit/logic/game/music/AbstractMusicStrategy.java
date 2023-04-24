package net.imshit.logic.game.music;

/**
 * @author Jim
 */
public abstract class AbstractMusicStrategy {

    /**
     * 设置BGM类型
     *
     * @param bgmType 普通/BOSS/无
     */
    public abstract void setBgm(BgmType bgmType);

    /**
     * 播放爆炸声
     */
    public abstract void playBombExplosion();

    /**
     * 播放子弹发射
     */

    public abstract void playBullet();

    /**
     * 播放子弹命中
     */

    public abstract void playBulletHit();

    /**
     * 播放获取到掉落物
     */

    public abstract void playGetSupply();

    /**
     * 播放游戏结束提示音
     */

    public abstract void playGameOver();

    public enum BgmType {
        /* 普通 */
        NORMAL,
        /* BOSS */
        BOSS,
        /* 静音 */
        NONE
    }
}
