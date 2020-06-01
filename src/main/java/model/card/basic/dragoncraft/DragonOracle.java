package model.card.basic.dragoncraft;

import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.spell.Spell;
import model.effect.EffectBase;
import model.exception.GameEndingException;

public class DragonOracle extends Spell {
    public DragonOracle(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.DRAGONCRAFT, 2, new EffectBase(leader) {
            @Override
            public void settle() throws GameEndingException {
                final boolean drawingCondition = leader.isOverflow();
                final Leader leader = (Leader) getOwner();
                leader.incrementMaximumPp();
                if (drawingCondition) {
                    leader.drawCard(1);
                }
            }
        });
    }

    @Override
    public String getName() {
        return "Dragon Oracle";
    }
}
