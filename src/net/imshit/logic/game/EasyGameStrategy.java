package net.imshit.logic.game;

import net.imshit.logic.game.generate.EasyGenerateStrategy;

/**
 * 简单难度的游戏
 *
 * @author Jim
 */
public class EasyGameStrategy extends AbstractGameStrategy {
    public EasyGameStrategy() {
        this.generateStrategy = new EasyGenerateStrategy();
    }
}
