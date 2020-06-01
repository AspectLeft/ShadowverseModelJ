package model.card.basic.havencraft;

import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.amulet.CountdownAmulet;
import model.card.basic.havencraft.token.Pegasus;
import model.effect.LastWords;
import model.exception.GameEndingException;

public class SummonPegasus extends CountdownAmulet {
    public SummonPegasus(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.HAVENCRAFT, 1, 4);
    }

    @Override
    protected void initEffects() {
        final LastWords lastWords = new LastWords(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final Pegasus pegasus = new Pegasus(owner.getLeader());
                owner.getLeader().summon(pegasus);
            }
        };
        effects.add(lastWords);
    }

    @Override
    public String getName() {
        return "Summon Pegasus";
    }

}
