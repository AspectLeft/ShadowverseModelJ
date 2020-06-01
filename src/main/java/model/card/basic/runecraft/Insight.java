package model.card.basic.runecraft;

import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.spell.Spell;
import model.effect.EffectBase;
import model.exception.GameEndingException;

public class Insight extends Spell {
    public Insight(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.RUNECRAFT, 1, new EffectBase(leader) {
            @Override
            public void settle() throws GameEndingException {
                owner.getLeader().drawCard(1);
            }
        });
    }

    @Override
    public String getName() {
        return "Insight";
    }
}
