package net.imshit.aircraft.hero;

import net.imshit.aircraft.enemy.EliteEnemy;
import net.imshit.application.ImageManager;
import net.imshit.application.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    private HeroAircraft heroAircraft;
    private final int initialX = Main.WINDOW_WIDTH / 2;
    private final int initialY = Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight();
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
    @DisplayName("Test `decreaseHp` method, `increaseHp` and `getHp` method")
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
        // should make no action
        assertAll(
                () -> assertEquals(this.initialX, this.heroAircraft.getLocationX()),
                () -> assertEquals(this.initialY, this.heroAircraft.getLocationY()),
                () -> assertEquals(this.initialSpeedY, this.heroAircraft.getSpeedY()),
                () -> assertEquals(this.initialHp, this.heroAircraft.getHp())
        );
    }

    @Test
    @DisplayName("Test crash detection")
    void crash() {
        // 中心完全重合
        var same = new EliteEnemy(this.initialX, this.initialY, 0, 0, 20);
        assertTrue(this.heroAircraft.crash(same));
        var midWidthDis = (this.heroAircraft.getWidth() + same.getWidth()) / 2;
        // 右侧挨着判定框
        var right0 = new EliteEnemy(this.initialX + midWidthDis, this.initialY, 0, 0, 100);
        assertFalse(this.heroAircraft.crash(right0));
        // 右侧恰好进入判定框
        var right1 = new EliteEnemy(this.initialX + midWidthDis - 1, this.initialY, 0, 0, 100);
        assertTrue(this.heroAircraft.crash(right1));
        // 左侧挨着判定框
        var left0 = new EliteEnemy(this.initialX - midWidthDis, this.initialY, 0, 0, 100);
        assertFalse(this.heroAircraft.crash(left0));
        // 左侧恰好进入判定框
        var left1 = new EliteEnemy(this.initialX - midWidthDis + 1, this.initialY, 0, 0, 100);
        assertTrue(this.heroAircraft.crash(left1));

        var midHeightDis = (this.heroAircraft.getHeight() + same.getHeight()) / 2 / 2;
        // 上侧挨着判定框
        var up0 = new EliteEnemy(this.initialX, this.initialY - midHeightDis, 0, 0, 100);
        assertFalse(this.heroAircraft.crash(up0));
        // 上侧恰好进入判定框
        var up1 = new EliteEnemy(this.initialX, this.initialY - midHeightDis + 1, 0, 0, 100);
        assertTrue(this.heroAircraft.crash(up1));
        // 左侧挨着判定框
        var down0 = new EliteEnemy(this.initialX, this.initialY + midHeightDis, 0, 0, 100);
        assertFalse(this.heroAircraft.crash(down0));
        // 左侧恰好进入判定框
        var down1 = new EliteEnemy(this.initialX, this.initialY + midHeightDis - 1, 0, 0, 100);
        assertTrue(this.heroAircraft.crash(down1));
    }

    @Test
    @DisplayName("Test `setLocation`, `getLocationX` and `getLocationY` method")
    void locationGetSet() {
        assertAll(
                () -> assertEquals(this.initialX, this.heroAircraft.getLocationX()),
                () -> assertEquals(this.initialY, this.heroAircraft.getLocationY())
        );
        this.heroAircraft.setLocation(114, 514);
        assertAll(
                () -> assertEquals(114, this.heroAircraft.getLocationX()),
                () -> assertEquals(514, this.heroAircraft.getLocationY())
        );
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