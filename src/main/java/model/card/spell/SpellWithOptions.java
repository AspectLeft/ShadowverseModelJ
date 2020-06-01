package model.card.spell;

import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.effect.Choice;
import model.effect.EffectBase;
import model.effect.EffectWithOptions;
import model.exception.GameEndingException;

import java.util.List;

public abstract class SpellWithOptions extends Spell {
    protected SpellWithOptions(final Leader leader, final Rarity rarity, final CardClass defaultCardClass,
                               final int defaultCost, final EffectBase effect) {
        super(leader, rarity, defaultCardClass, defaultCost, effect);
    }

    @Override
    public void play(final List<Choice> options) throws GameEndingException {
        assert effect != null;
        ((EffectWithOptions) effect).setOptions(options);
        super.play(options);
    }

    public abstract List<List<Choice>> getChoices();
}
