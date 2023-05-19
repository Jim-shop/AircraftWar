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

package net.imshit.aircraft.hero;

import net.imshit.Config;
import net.imshit.element.aircraft.enemy.EliteEnemy;
import net.imshit.element.aircraft.hero.HeroAircraft;
import net.imshit.element.prop.BloodProp;
import net.imshit.util.generate.prop.EasyPropGenerateStrategy;
import net.imshit.util.resource.ImageManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    private HeroAircraft heroAircraft;
    private final int initialX = Config.WINDOW_WIDTH / 2;
    private final int initialY = Config.WINDOW_HEIGHT - ImageManager.get(HeroAircraft.class).getHeight();
    private final int initialSpeedY = 0;
    private final int initialHp = 1000;
    private final int shootNum = 1;

    @BeforeEach
    void setUp() {
        this.heroAircraft = HeroAircraft.getInstance();
        this.heroAircraft.setLocation(initialX, initialY);
        this.heroAircraft.increaseHp(1000);
    }

    @Test
    @DisplayName("Test `decreaseHp`, `increaseHp`, `getHp` method")
    void hpCalcTest() {
        this.heroAircraft.decreaseHp(123);
        assertEquals(this.initialHp - 123, this.heroAircraft.getHp());
        this.heroAircraft.decreaseHp(0);
        assertEquals(this.initialHp - 123, this.heroAircraft.getHp());
        this.heroAircraft.decreaseHp(114514);
        assertEquals(0, this.heroAircraft.getHp());
        this.heroAircraft.decreaseHp(2147483647);
        assertEquals(0, this.heroAircraft.getHp());
        this.heroAircraft.decreaseHp(-100);
        assertEquals(0, this.heroAircraft.getHp());

        this.heroAircraft.increaseHp(this.initialHp);

        this.heroAircraft.increaseHp(123);
        assertEquals(this.initialHp, this.heroAircraft.getHp());
        this.heroAircraft.increaseHp(0);
        assertEquals(this.initialHp, this.heroAircraft.getHp());
        this.heroAircraft.decreaseHp(514);
        this.heroAircraft.increaseHp(114);
        assertEquals(this.initialHp + 114 - 514, this.heroAircraft.getHp());
        this.heroAircraft.increaseHp(2147483647);
        assertEquals(this.initialHp, this.heroAircraft.getHp());
        this.heroAircraft.increaseHp(-100);
        assertEquals(this.initialHp, this.heroAircraft.getHp());
    }

    @Test
    @DisplayName("Test `forward` method")
    void forward() {
        this.heroAircraft.forward();
        // should make no action
        assertAll(() -> assertEquals(this.initialX, this.heroAircraft.getLocationX()), () -> assertEquals(this.initialY, this.heroAircraft.getLocationY()), () -> assertEquals(this.initialSpeedY, this.heroAircraft.getSpeedY()), () -> assertEquals(this.initialHp, this.heroAircraft.getHp()));
    }

    @Test
    @DisplayName("Test plane crash detection")
    void planeCrash() {
        // 中心完全重合
        var same = new EliteEnemy(this.initialX, this.initialY, 0, 0, 20, 20, new EasyPropGenerateStrategy());
        assertTrue(this.heroAircraft.crash(same));
        var midWidthDis = (this.heroAircraft.getWidth() + same.getWidth()) / 2;
        assertAll(
                // 右侧挨着判定框
                () -> assertFalse(this.heroAircraft.crash(new EliteEnemy(this.initialX + midWidthDis, this.initialY, 0, 0, 100, 20, new EasyPropGenerateStrategy()))),
                // 右侧恰好进入判定框
                () -> assertTrue(this.heroAircraft.crash(new EliteEnemy(this.initialX + midWidthDis - 1, this.initialY, 0, 0, 100, 20, new EasyPropGenerateStrategy()))),
                // 左侧挨着判定框
                () -> assertFalse(this.heroAircraft.crash(new EliteEnemy(this.initialX - midWidthDis, this.initialY, 0, 0, 100, 20, new EasyPropGenerateStrategy()))),
                // 左侧恰好进入判定框
                () -> assertTrue(this.heroAircraft.crash(new EliteEnemy(this.initialX - midWidthDis + 1, this.initialY, 0, 0, 100, 20, new EasyPropGenerateStrategy()))));
        var midHeightDis = (this.heroAircraft.getHeight() + same.getHeight()) / 2 / 2;
        assertAll(
                // 上侧挨着判定框
                () -> assertFalse(this.heroAircraft.crash(new EliteEnemy(this.initialX, this.initialY - midHeightDis, 0, 0, 100, 20, new EasyPropGenerateStrategy()))),
                // 上侧恰好进入判定框
                () -> assertTrue(this.heroAircraft.crash(new EliteEnemy(this.initialX, this.initialY - midHeightDis + 1, 0, 0, 100, 20, new EasyPropGenerateStrategy()))),
                // 左侧挨着判定框
                () -> assertFalse(this.heroAircraft.crash(new EliteEnemy(this.initialX, this.initialY + midHeightDis, 0, 0, 100, 20, new EasyPropGenerateStrategy()))),
                // 左侧恰好进入判定框
                () -> assertTrue(this.heroAircraft.crash(new EliteEnemy(this.initialX, this.initialY + midHeightDis - 1, 0, 0, 100, 20, new EasyPropGenerateStrategy())))

        );
    }

    @Test
    @DisplayName("Test other object crash detection")
    void objectCrash() {
        // 中心完全重合
        var same = new BloodProp(this.initialX, this.initialY, 0, 0);
        assertTrue(this.heroAircraft.crash(same));

        var midWidthDis = (this.heroAircraft.getWidth() + same.getWidth()) / 2;
        assertAll(
                // 右侧挨着判定框
                () -> assertFalse(this.heroAircraft.crash(new BloodProp(this.initialX + midWidthDis, this.initialY, 0, 0))),
                // 右侧恰好进入判定框
                () -> assertTrue(this.heroAircraft.crash(new BloodProp(this.initialX + midWidthDis - 1, this.initialY, 0, 0))),
                // 左侧挨着判定框
                () -> assertFalse(this.heroAircraft.crash(new BloodProp(this.initialX - midWidthDis, this.initialY, 0, 0))),
                // 左侧恰好进入判定框
                () -> assertTrue(this.heroAircraft.crash(new BloodProp(this.initialX - midWidthDis + 1, this.initialY, 0, 0))));
        var midHeightDis = (this.heroAircraft.getHeight() / 2 + same.getHeight()) / 2;
        assertAll(
                // 上侧挨着判定框
                () -> assertFalse(this.heroAircraft.crash(new BloodProp(this.initialX, this.initialY - midHeightDis, 0, 0))),
                // 上侧恰好进入判定框
                () -> assertTrue(this.heroAircraft.crash(new BloodProp(this.initialX, this.initialY - midHeightDis + 1, 0, 0))),
                // 左侧挨着判定框
                () -> assertFalse(this.heroAircraft.crash(new BloodProp(this.initialX, this.initialY + midHeightDis, 0, 0))),
                // 左侧恰好进入判定框
                () -> assertTrue(this.heroAircraft.crash(new BloodProp(this.initialX, this.initialY + midHeightDis - 1, 0, 0))));
    }

    @Test
    @DisplayName("Test `setLocation`, `getLocationX` and `getLocationY` method")
    void locationGetSet() {
        assertAll(() -> assertEquals(this.initialX, this.heroAircraft.getLocationX()), () -> assertEquals(this.initialY, this.heroAircraft.getLocationY()));
        this.heroAircraft.setLocation(114, 514);
        assertAll(() -> assertEquals(114, this.heroAircraft.getLocationX()), () -> assertEquals(514, this.heroAircraft.getLocationY()));
    }

    @Test
    @DisplayName("Test `getImage`, `getSpeedY`, `getWidth`, `getHeight`")
    void infoGet() {
        var image = ImageManager.get(HeroAircraft.class.getName());
        assertEquals(image, this.heroAircraft.getImage());
        assertEquals(this.initialSpeedY, this.heroAircraft.getSpeedY());
        assertEquals(ImageManager.get(HeroAircraft.class.getName()).getWidth(), this.heroAircraft.getWidth());
        assertEquals(ImageManager.get(HeroAircraft.class.getName()).getHeight(), this.heroAircraft.getHeight());
    }

    @Test
    @DisplayName("Test `vanish` method")
    void vanish() {
        assertFalse(this.heroAircraft.notValid());
        this.heroAircraft.vanish();
        assertTrue(this.heroAircraft.notValid());
    }

    @Test
    @DisplayName("Test singleton uniqueness")
    void getInstance() {
        assertEquals(this.heroAircraft, HeroAircraft.getInstance());
    }

    @Test
    @DisplayName("Test `shoot` method")
    void shoot() {
        assertEquals(this.shootNum, this.heroAircraft.shoot().size());
    }
}