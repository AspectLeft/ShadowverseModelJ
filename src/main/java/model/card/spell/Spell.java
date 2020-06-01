package model.card.spell;

import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.card.Rarity;
import model.effect.Choice;
import model.effect.EffectBase;
import model.exception.GameEndingException;
import model.exception.IllegalOperationException;

import java.util.List;

public abstract class Spell extends CardBase {
    protected final EffectBase effect;

    private Spell() {
        super(null, Rarity.BRONZE, CardClass.NEUTRAL,0);
        effect = null;
    }

    protected Spell(final Leader leader, final Rarity rarity, final CardClass defaultCardClass, final int defaultCost,
                    final EffectBase effect) {
        super(leader, rarity, defaultCardClass, defaultCost);
        this.effect = effect;
    }

    @Override
    public void play(final List<Choice> options) throws GameEndingException {
        for (final CardBase card: getLeader().getHand()) {
            if (card instanceof Spellboost) {
                ((Spellboost) card).increaseSpellboost(1);
            }
        }

        getGame().pushEffect(effect);
        getGame().pushEffect(new EffectBase(getLeader()) {
            @Override
            public void settle() throws GameEndingException {
                owner.getLeader().increaseShadow(1);
            }
        });
        super.play(options);
    }

    @Override
    public void takeDamage(int damageValue) throws IllegalOperationException {
        throw new IllegalOperationException("Spell cannot take damage");
    }
}
