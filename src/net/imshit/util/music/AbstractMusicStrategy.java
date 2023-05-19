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

package net.imshit.util.music;

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
