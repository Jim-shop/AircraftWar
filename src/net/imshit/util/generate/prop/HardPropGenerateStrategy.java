package net.imshit.util.generate.prop;

/**
 * 随机道具工厂
 *
 * @author Jim
 */
public class HardPropGenerateStrategy extends AbstractPropGenerateStrategy {
    public HardPropGenerateStrategy() {
        this.bloodProbability = 0.1;
        this.bombProbability = 0.1;
        this.bulletProbability = 0.1;
    }
}
