package net.imshit.logic.generate.enemy;

import net.imshit.element.aircraft.enemy.AbstractEnemy;
import net.imshit.element.aircraft.enemy.factory.AbstractEnemyFactory;

import java.util.List;

/**
 * @author Jim
 */
public abstract class AbstractEnemyGenerateStrategy {
    protected AbstractEnemyFactory mobFactory, eliteFactory, bossFactory;

    private int time, score;

    protected double mobProbability;
    protected int enemyMaxNumber;
    protected int bossScoreThreshold;
    protected int enemySummonInterval;
    protected int enemyShootInterval;
    protected int heroShootInterval;

    protected double hpIncreaseRate, powerIncreaseRate, speedIncreaseRate, bossHpIncreaseRate;
    protected int mobBaseHp, eliteBaseHp, bossBaseHp;
    protected int eliteBasePower, bossBasePower;
    protected float mobBaseSpeed, eliteBaseSpeed, bossBaseSpeed;

    private int lastBossSummonScore;
    private int lastEnemySummonTime;
    private int lastEnemyShootTime;
    private int lastHeroShootTime;

    public void inform(int time, int score) {
        this.time = time;
        this.score = score;
    }

    public List<AbstractEnemy> generateEnemy(int currentEnemyNum) {
        if (currentEnemyNum < this.enemyMaxNumber) {
            if (Math.random() < this.mobProbability) {
                return List.of(this.mobFactory.createEnemy((int) (mobBaseHp + score * hpIncreaseRate), 0, (float) (mobBaseSpeed + score * speedIncreaseRate)));
            } else {
                return List.of(this.eliteFactory.createEnemy((int) (eliteBaseHp + score * hpIncreaseRate), (int) (eliteBasePower + score * powerIncreaseRate), (float) (eliteBaseSpeed + score * speedIncreaseRate)));
            }
        } else {
            return List.of();
        }
    }

    public AbstractEnemy generateBoss() {
        return bossFactory.createEnemy((int) (bossBaseHp + lastBossSummonScore * bossHpIncreaseRate), (int) (bossBasePower + score * powerIncreaseRate), (float) (bossBaseSpeed + score * speedIncreaseRate));
    }

    public boolean isTimeToGenerateBoss(AbstractEnemy currentBoss) {
        if (currentBoss == null && score - this.lastBossSummonScore > this.bossScoreThreshold) {
            this.lastBossSummonScore = score;
            return true;
        } else {
            return false;
        }
    }

    public boolean isTimeToGenerateEnemy() {
        if (time - this.lastEnemySummonTime > this.enemySummonInterval) {
            this.lastEnemySummonTime = time;
            return true;
        } else {
            return false;
        }
    }

    public boolean isTimeForEnemyShoot() {
        if (time - this.lastEnemyShootTime > this.enemyShootInterval) {
            this.lastEnemyShootTime = time;
            return true;
        } else {
            return false;
        }
    }

    public boolean isTimeForHeroShoot() {
        if (time - this.lastHeroShootTime > this.heroShootInterval) {
            this.lastHeroShootTime = time;
            return true;
        } else {
            return false;
        }
    }


    public void reset() {
        this.lastEnemySummonTime = 0;
        this.lastBossSummonScore = 0;
        this.lastEnemyShootTime = 0;
        this.lastHeroShootTime = 0;
    }

}
