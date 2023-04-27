package net.imshit.element.aircraft.enemy;

import net.imshit.element.prop.AbstractProp;
import net.imshit.logic.generate.prop.AbstractPropGenerateStrategy;

import java.util.List;

/**
 * 精英敌机
 * 可射击
 *
 * @author jim
 */
public class EliteEnemy extends AbstractEnemy {
    private final AbstractPropGenerateStrategy propGenerateStrategy;

    public EliteEnemy(float locationX, float locationY, float speedX, float speedY, int hp, int power, AbstractPropGenerateStrategy propGenerateStrategy) {
        super(locationX, locationY, speedX, speedY, hp, power, 1);
        this.propGenerateStrategy = propGenerateStrategy;
    }

    @Override
    public List<AbstractProp> prop() {
        var prop = propGenerateStrategy.createProp(this.locationX, this.locationY);
        if (prop != null) {
            return List.of(prop);
        } else {
            return List.of();
        }
    }

    @Override
    public int getCredits() {
        return 60;
    }
}
