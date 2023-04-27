package net.imshit.logic.generate.enemy;

import net.imshit.element.aircraft.enemy.AbstractEnemy;

import java.util.List;

/**
 * @author Jim
 */
public abstract class AbstractEnemyGenerateStrategy {
    protected double mobProbability;
    protected int enemyMaxNumber;
    protected int bossScoreThreshold;
    protected int enemySummonInterval;
    protected int enemyShootInterval;
    protected int heroShootInterval;

    private int lastBossSummonScore;
    private int lastEnemySummonTime;
    private int lastEnemyShootTime;
    private int lastHeroShootTime;

    /**
     * 生成敌机
     *
     * @param currentEnemyNum 场上的敌机数量
     * @return 生成的敌机列表
     */
    public abstract List<AbstractEnemy> generateEnemy(int currentEnemyNum);

    /**
     * 生成BOSS机
     *
     * @return 生成的BOSS
     */
    public abstract AbstractEnemy generateBoss();

    public boolean isTimeToGenerateBoss(AbstractEnemy currentBoss, int score) {
        if (currentBoss == null && score - this.lastBossSummonScore > this.bossScoreThreshold) {
            this.lastBossSummonScore = score;
            return true;
        } else {
            return false;
        }
    }

    public boolean isTimeToGenerateEnemy(int time) {
        if (time - this.lastEnemySummonTime > this.enemySummonInterval) {
            this.lastEnemySummonTime = time;
            return true;
        } else {
            return false;
        }
    }

    public boolean isTimeForEnemyShoot(int time) {
        if (time - this.lastEnemyShootTime > this.enemyShootInterval) {
            this.lastEnemyShootTime = time;
            return true;
        } else {
            return false;
        }
    }

    public boolean isTimeForHeroShoot(int time) {
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
