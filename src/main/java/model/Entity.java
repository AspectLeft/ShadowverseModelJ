package model;

import model.effect.Choice;
import model.effect.ChoiceType;
import model.effect.DamageEffect;
import model.event.EvalDamageEvent;
import model.exception.GameEndingException;
import model.exception.IllegalOperationException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Base of CardBase and Leader
 */
public abstract class Entity {
    public abstract Leader getLeader();

    public abstract String getName();

    public abstract void takeDamage(final int damageValue) throws IllegalOperationException, GameEndingException;

    public abstract Game getGame();


    // Try to give damage to a target, evaluating any auras.
    protected void giveDamage(final DamageType type, final int damageValue, final Entity target)
            throws GameEndingException {
        if (target == null) return;
        buildDamageEffect(type, damageValue, target).settle();
    }

    protected void giveDamage(final DamageType type, final int damageValue, final List<? extends Entity> targetList)
            throws GameEndingException {
        targetList.stream().map(target -> buildDamageEffect(type, damageValue, target)).collect(Collectors.toList())
                .forEach(DamageEffect::settle);
    }

    private DamageEffect buildDamageEffect(final DamageType type, final int damageValue, final Entity target) {
        final Damage damage = new Damage(damageValue, type);
        getGame().evalDamage(new EvalDamageEvent(this, target, damage));
        final DamageEffect damageEffect = new DamageEffect(target);
        damageEffect.setOptions(Collections.singletonList(Choice.builder().type(ChoiceType.DAMAGE)
                .value(damage.getValue()).build()));
        return damageEffect;
    }

    public void effectDamage(final int damageValue, final Entity target) throws GameEndingException {
        giveDamage(DamageType.EFFECT, damageValue, target);
    }

    public void effectDamage(final int damageValue, final List<? extends Entity> targetList) throws GameEndingException {
        giveDamage(DamageType.EFFECT, damageValue, targetList);
    }
}
