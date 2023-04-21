package net.imshit.aircraft.enemy;

import net.imshit.Config;
import net.imshit.element.aircraft.enemy.EliteEnemy;
import net.imshit.element.prop.BloodProp;
import net.imshit.io.resource.ImageManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class EliteEnemyTest {

    private EliteEnemy eliteEnemy;
    private final int initialX = 114;
    private final int initialY = 514;
    private final int initialSpeedX = 0;
    private final int initialSpeedY = 10;
    private final int initialHp = 20;
    private final int shootNum = 1;

    @BeforeEach
    void setUp() {
        this.eliteEnemy = new EliteEnemy(initialX, initialY, initialSpeedX, initialSpeedY, initialHp);
    }

    @AfterEach
    void cleanUp() {
        this.eliteEnemy = null;
    }

    @Test
    @DisplayName("Test `increaseHp`, `getHp`")
    void hpCalc() {
        assertEquals(this.initialHp, this.eliteEnemy.getHp());
        this.eliteEnemy.decreaseHp(1);
        assertEquals(this.initialHp - 1, this.eliteEnemy.getHp());
        this.eliteEnemy.decreaseHp(0);
        assertEquals(this.initialHp - 1, this.eliteEnemy.getHp());
        this.eliteEnemy.decreaseHp(2147483647);
        assertEquals(0, this.eliteEnemy.getHp());
        this.eliteEnemy.decreaseHp(-2147483648);
        assertEquals(0, this.eliteEnemy.getHp());
    }

    @Test
    @DisplayName("Test `forward`, `getLocationX`, `getLocationY`, `getSpeedY`, `getHp`, `notValid`")
    void forward() {
        assertAll(
                () -> assertEquals(this.initialX, this.eliteEnemy.getLocationX()),
                () -> assertEquals(this.initialY, this.eliteEnemy.getLocationY()),
                () -> assertEquals(this.initialSpeedY, this.eliteEnemy.getSpeedY()),
                () -> assertEquals(this.initialHp, this.eliteEnemy.getHp())
        );
        this.eliteEnemy.forward();
        assertAll(
                () -> assertEquals(this.initialX, this.eliteEnemy.getLocationX()),
                () -> assertEquals(this.initialY + this.initialSpeedY, this.eliteEnemy.getLocationY()),
                () -> assertEquals(this.initialSpeedY, this.eliteEnemy.getSpeedY()),
                () -> assertEquals(this.initialHp, this.eliteEnemy.getHp())
        );
        assertFalse(this.eliteEnemy.notValid());
        for (var iter = (Config.WINDOW_HEIGHT - this.initialY) / this.initialSpeedY + 5; iter >= 0; iter--) {
            this.eliteEnemy.forward();
        }
        assertTrue(this.eliteEnemy.notValid());
    }


    @Test
    @DisplayName("Test plane crash detection")
    void planeCrash() {
        // 中心完全重合
        var same = new EliteEnemy(this.initialX, this.initialY, 0, 0, 20);
        assertTrue(this.eliteEnemy.crash(same));
        var midWidthDis = (this.eliteEnemy.getWidth() + same.getWidth()) / 2;
        assertAll(
                // 右侧挨着判定框
                () -> assertFalse(this.eliteEnemy.crash(new EliteEnemy(this.initialX + midWidthDis, this.initialY, 0, 0, 100))),
                // 右侧恰好进入判定框
                () -> assertTrue(this.eliteEnemy.crash(new EliteEnemy(this.initialX + midWidthDis - 1, this.initialY, 0, 0, 100))),
                // 左侧挨着判定框
                () -> assertFalse(this.eliteEnemy.crash(new EliteEnemy(this.initialX - midWidthDis, this.initialY, 0, 0, 100))),
                // 左侧恰好进入判定框
                () -> assertTrue(this.eliteEnemy.crash(new EliteEnemy(this.initialX - midWidthDis + 1, this.initialY, 0, 0, 100)))
        );
        var midHeightDis = (this.eliteEnemy.getHeight() + same.getHeight()) / 2 / 2;
        assertAll(
                // 上侧挨着判定框
                () -> assertFalse(this.eliteEnemy.crash(new EliteEnemy(this.initialX, this.initialY - midHeightDis, 0, 0, 100))),
                // 上侧恰好进入判定框
                () -> assertTrue(this.eliteEnemy.crash(new EliteEnemy(this.initialX, this.initialY - midHeightDis + 1, 0, 0, 100))),
                // 左侧挨着判定框
                () -> assertFalse(this.eliteEnemy.crash(new EliteEnemy(this.initialX, this.initialY + midHeightDis, 0, 0, 100))),
                // 左侧恰好进入判定框
                () -> assertTrue(this.eliteEnemy.crash(new EliteEnemy(this.initialX, this.initialY + midHeightDis - 1, 0, 0, 100)))

        );
    }

    @Test
    @DisplayName("Test other object crash detection")
    void objectCrash() {
        // 中心完全重合
        var same = new BloodProp(this.initialX, this.initialY, 0, 0);
        assertTrue(this.eliteEnemy.crash(same));

        var midWidthDis = (this.eliteEnemy.getWidth() + same.getWidth()) / 2;
        assertAll(
                // 右侧挨着判定框
                () -> assertFalse(this.eliteEnemy.crash(new BloodProp(this.initialX + midWidthDis, this.initialY, 0, 0))),
                // 右侧恰好进入判定框
                () -> assertTrue(this.eliteEnemy.crash(new BloodProp(this.initialX + midWidthDis - 1, this.initialY, 0, 0))),
                // 左侧挨着判定框
                () -> assertFalse(this.eliteEnemy.crash(new BloodProp(this.initialX - midWidthDis, this.initialY, 0, 0))),
                // 左侧恰好进入判定框
                () -> assertTrue(this.eliteEnemy.crash(new BloodProp(this.initialX - midWidthDis + 1, this.initialY, 0, 0)))
        );
        var midHeightDis = (this.eliteEnemy.getHeight() / 2 + same.getHeight()) / 2;
        assertAll(
                // 上侧挨着判定框
                () -> assertFalse(this.eliteEnemy.crash(new BloodProp(this.initialX, this.initialY - midHeightDis, 0, 0))),
                // 上侧恰好进入判定框
                () -> assertTrue(this.eliteEnemy.crash(new BloodProp(this.initialX, this.initialY - midHeightDis + 1, 0, 0))),
                // 左侧挨着判定框
                () -> assertFalse(this.eliteEnemy.crash(new BloodProp(this.initialX, this.initialY + midHeightDis, 0, 0))),
                // 左侧恰好进入判定框
                () -> assertTrue(this.eliteEnemy.crash(new BloodProp(this.initialX, this.initialY + midHeightDis - 1, 0, 0)))
        );
    }

    @Test
    @DisplayName("Test `setLocation`, `getLocationX` and `getLocationY` method")
    void locationGetSet() {
        assertAll(
                () -> assertEquals(this.initialX, this.eliteEnemy.getLocationX()),
                () -> assertEquals(this.initialY, this.eliteEnemy.getLocationY())
        );
        this.eliteEnemy.setLocation(114, 514);
        assertAll(
                () -> assertEquals(114, this.eliteEnemy.getLocationX()),
                () -> assertEquals(514, this.eliteEnemy.getLocationY())
        );
    }

    @Test
    @DisplayName("Test `getImage`, `getSpeedY`, `getWidth`, `getHeight`")
    void infoGet() {
        var image = ImageManager.get(EliteEnemy.class.getName());
        assertEquals(image, this.eliteEnemy.getImage());
        assertEquals(this.initialSpeedY, this.eliteEnemy.getSpeedY());
        assertEquals(ImageManager.get(EliteEnemy.class.getName()).getWidth(), this.eliteEnemy.getWidth());
        assertEquals(ImageManager.get(EliteEnemy.class.getName()).getHeight(), this.eliteEnemy.getHeight());
    }

    @Test
    @DisplayName("Test `vanish` method")
    void vanish() {
        assertFalse(this.eliteEnemy.notValid());
        this.eliteEnemy.vanish();
        assertTrue(this.eliteEnemy.notValid());
    }

    @Test
    @DisplayName("Test `shoot` method")
    void shoot() {
        assertEquals(this.shootNum, this.eliteEnemy.shoot().size());
    }

    @DisplayName("Test `prop` method")
    @RepeatedTest(514)
    void prop() {
        assertTrue(this.eliteEnemy.prop().size() <= 1);
    }
}