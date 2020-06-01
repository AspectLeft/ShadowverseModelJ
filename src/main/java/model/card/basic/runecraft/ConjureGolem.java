package model.card.basic.runecraft;

import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.basic.runecraft.token.ClayGolem;
import model.card.spell.Spell;
import model.effect.EffectBase;
import model.exception.GameEndingException;

public final class ConjureGolem extends Spell {
    public ConjureGolem(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.RUNECRAFT, 2, new EffectBase(leader) {
            @Override
            public void settle() throws GameEndingException {
                owner.getLeader().summon(new ClayGolem(owner.getLeader()));
            }
        });
    }

    @Override
    public String getName() {
        return "Conjure Golem";
    }
}
