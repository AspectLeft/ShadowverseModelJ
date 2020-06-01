package model.card.basic.runecraft;

import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.basic.runecraft.token.ClayGolem;
import model.card.spell.Spell;
import model.effect.EffectBase;
import model.exception.GameEndingException;

import java.util.Arrays;

public final class ConjureTwosome extends Spell {
    public ConjureTwosome(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.RUNECRAFT, 4, new EffectBase(leader) {
            @Override
            public void settle() throws GameEndingException {
                owner.getLeader().summon(Arrays.asList(new ClayGolem(owner.getLeader()),
                        new ClayGolem(owner.getLeader())));
            }
        });
    }

    @Override
    public String getName() {
        return "Conjure Twosome";
    }
}
