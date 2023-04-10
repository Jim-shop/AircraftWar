package net.imshit.prop;

import net.imshit.aircraft.enemy.EliteEnemy;
import net.imshit.aircraft.hero.HeroAircraft;
import net.imshit.application.ImageManager;
import net.imshit.application.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BloodPropTest {

    private BloodProp bloodProp;
    private final int initialX = 114;
    private final int initialY = 514;
    private final int initialSpeedX = 0;
    private final int initialSpeedY = 10;

    @BeforeEach
    void setUp() {
        this.bloodProp = new BloodProp(initialX, initialY, initialSpeedX, initialSpeedY);
    }

    @AfterEach
    void cleanUp() {
        this.bloodProp = null;
    }

    @Test
    @DisplayName("Test `forward`, `getLocationX`, `getLocationY`, `getSpeedY`, `getHp`, `notValid`")
    void forward() {
        assertAll(
                () -> assertEquals(this.initialX, this.bloodProp.getLocationX()),
                () -> assertEquals(this.initialY, this.bloodProp.getLocationY()),
                () -> assertEquals(this.initialSpeedY, this.bloodProp.getSpeedY())
        );
        this.bloodProp.forward();
        assertAll(
                () -> assertEquals(this.initialX, this.bloodProp.getLocationX()),
                () -> assertEquals(this.initialY + this.initialSpeedY, this.bloodProp.getLocationY()),
                () -> assertEquals(this.initialSpeedY, this.bloodProp.getSpeedY())
        );
        assertFalse(this.bloodProp.notValid());
        for (var iter = (Main.WINDOW_HEIGHT - this.initialY) / this.initialSpeedY + 5; iter >= 0; iter--) {
            this.bloodProp.forward();
        }
        assertTrue(this.bloodProp.notValid());
    }


    @Test
    @DisplayName("Test plane crash detection")
    void planeCrash() {
        // 中心完全重合
        var same = new EliteEnemy(this.initialX, this.initialY, 0, 0, 20);
        assertTrue(this.bloodProp.crash(same));
        var midWidthDis = (this.bloodProp.getWidth() + same.getWidth()) / 2;
        assertAll(
                // 右侧挨着判定框
                () -> assertFalse(this.bloodProp.crash(new EliteEnemy(this.initialX + midWidthDis, this.initialY, 0, 0, 100))),
                // 右侧恰好进入判定框
                () -> assertTrue(this.bloodProp.crash(new EliteEnemy(this.initialX + midWidthDis - 1, this.initialY, 0, 0, 100))),
                // 左侧挨着判定框
                () -> assertFalse(this.bloodProp.crash(new EliteEnemy(this.initialX - midWidthDis, this.initialY, 0, 0, 100))),
                // 左侧恰好进入判定框
                () -> assertTrue(this.bloodProp.crash(new EliteEnemy(this.initialX - midWidthDis + 1, this.initialY, 0, 0, 100)))
        );
        var midHeightDis = (this.bloodProp.getHeight() + same.getHeight() / 2) / 2;
        assertAll(
                // 上侧挨着判定框
                () -> assertFalse(this.bloodProp.crash(new EliteEnemy(this.initialX, this.initialY - midHeightDis, 0, 0, 100))),
                // 上侧恰好进入判定框
                () -> assertTrue(this.bloodProp.crash(new EliteEnemy(this.initialX, this.initialY - midHeightDis + 1, 0, 0, 100))),
                // 左侧挨着判定框
                () -> assertFalse(this.bloodProp.crash(new EliteEnemy(this.initialX, this.initialY + midHeightDis, 0, 0, 100))),
                // 左侧恰好进入判定框
                () -> assertTrue(this.bloodProp.crash(new EliteEnemy(this.initialX, this.initialY + midHeightDis - 1, 0, 0, 100)))

        );
    }

    @Test
    @DisplayName("Test other object crash detection")
    void objectCrash() {
        // 中心完全重合
        var same = new BloodProp(this.initialX, this.initialY, 0, 0);
        assertTrue(this.bloodProp.crash(same));

        var midWidthDis = (this.bloodProp.getWidth() + same.getWidth()) / 2;
        assertAll(
                // 右侧挨着判定框
                () -> assertFalse(this.bloodProp.crash(new BloodProp(this.initialX + midWidthDis, this.initialY, 0, 0))),
                // 右侧恰好进入判定框
                () -> assertTrue(this.bloodProp.crash(new BloodProp(this.initialX + midWidthDis - 1, this.initialY, 0, 0))),
                // 左侧挨着判定框
                () -> assertFalse(this.bloodProp.crash(new BloodProp(this.initialX - midWidthDis, this.initialY, 0, 0))),
                // 左侧恰好进入判定框
                () -> assertTrue(this.bloodProp.crash(new BloodProp(this.initialX - midWidthDis + 1, this.initialY, 0, 0)))
        );
        var midHeightDis = (this.bloodProp.getHeight() + same.getHeight()) / 2;
        assertAll(
                // 上侧挨着判定框
                () -> assertFalse(this.bloodProp.crash(new BloodProp(this.initialX, this.initialY - midHeightDis, 0, 0))),
                // 上侧恰好进入判定框
                () -> assertTrue(this.bloodProp.crash(new BloodProp(this.initialX, this.initialY - midHeightDis + 1, 0, 0))),
                // 左侧挨着判定框
                () -> assertFalse(this.bloodProp.crash(new BloodProp(this.initialX, this.initialY + midHeightDis, 0, 0))),
                // 左侧恰好进入判定框
                () -> assertTrue(this.bloodProp.crash(new BloodProp(this.initialX, this.initialY + midHeightDis - 1, 0, 0)))
        );
    }

    @Test
    @DisplayName("Test `setLocation`, `getLocationX` and `getLocationY` method")
    void locationGetSet() {
        assertAll(
                () -> assertEquals(this.initialX, this.bloodProp.getLocationX()),
                () -> assertEquals(this.initialY, this.bloodProp.getLocationY())
        );
        this.bloodProp.setLocation(114, 514);
        assertAll(
                () -> assertEquals(114, this.bloodProp.getLocationX()),
                () -> assertEquals(514, this.bloodProp.getLocationY())
        );
    }

    @Test
    @DisplayName("Test `getImage`, `getSpeedY`, `getWidth`, `getHeight`")
    void infoGet() {
        var image = ImageManager.get(BloodProp.class.getName());
        assertEquals(image, this.bloodProp.getImage());
        assertEquals(this.initialSpeedY, this.bloodProp.getSpeedY());
        assertEquals(ImageManager.get(BloodProp.class.getName()).getWidth(), this.bloodProp.getWidth());
        assertEquals(ImageManager.get(BloodProp.class.getName()).getHeight(), this.bloodProp.getHeight());
    }

    @Test
    @DisplayName("Test `vanish` method")
    void vanish() {
        assertFalse(this.bloodProp.notValid());
        this.bloodProp.vanish();
        assertTrue(this.bloodProp.notValid());
    }

    @Test
    @DisplayName("Test `use` method")
    void use() {
        var hero = HeroAircraft.getInstance();
        hero.decreaseHp(114514);
        assertEquals(0, hero.getHp());
        this.bloodProp.use(hero);
        assertEquals(100, hero.getHp());
        hero.increaseHp(811);
        assertEquals(911, hero.getHp());
        this.bloodProp.use(hero);
        assertEquals(1000, hero.getHp());
    }
}