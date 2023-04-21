package net.imshit.logic.game.generate;

import net.imshit.element.aircraft.enemy.AbstractEnemy;

import java.util.List;

/**
 * @author Jim
 */
public abstract class AbstractGenerateStrategy {
    protected int enemyMaxNumber;
    protected int bossScoreThreshold;
    protected double bossProbability;

    /**
     * 生成敌机
     *
     * @param currentEnemyNum 场上的敌机数量
     * @return 生成的敌机列表
     */
    public abstract List<AbstractEnemy> generateEnemy(int currentEnemyNum);
}
