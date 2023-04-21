package net.imshit.logic.game.generate;

import net.imshit.Config;
import net.imshit.element.aircraft.enemy.AbstractEnemy;

import java.util.List;

/**
 * @author Jim
 */
public abstract class AbstractGenerateStrategy {
    protected int generateInterval;
    protected double mobProbability;
    protected int enemyMaxNumber;
    protected int bossScoreThreshold;
    protected double bossProbability;

    private int time;

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
     * @param currentBoss 目前Boss
     * @param score       目前得分
     * @return 生成的BOSS
     */
    public abstract AbstractEnemy generateBoss(AbstractEnemy currentBoss, int score);

    public boolean isTimeToGenerate() {
        this.time += Config.REFRESH_INTERVAL;
        if (this.time > generateInterval) {
            this.time %= generateInterval;
            return true;
        } else {
            return false;
        }
    }
}
