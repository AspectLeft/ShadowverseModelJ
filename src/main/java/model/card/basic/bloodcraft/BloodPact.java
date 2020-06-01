package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.spell.Spell;
import model.effect.EffectBase;
import model.exception.GameEndingException;

public class BloodPact extends Spell {
    public BloodPact(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.BLOODCRAFT, 2, new EffectBase(leader) {
            @Override
            public void settle() throws GameEndingException {
                final Leader leader = owner.getLeader();
                leader.spellDamage(2, leader);
                leader.drawCard(2);
            }
        });
    }

    @Override
    public String getName() {
        return "Blood Pact";
    }
}
