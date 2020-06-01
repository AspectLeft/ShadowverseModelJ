package model.card.basic.havencraft;

import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.amulet.CountdownAmulet;
import model.card.basic.havencraft.token.HolyflameTiger;
import model.effect.LastWords;
import model.exception.GameEndingException;

import java.util.Arrays;

public final class DualFlames extends CountdownAmulet {
    public DualFlames(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.HAVENCRAFT, 5, 2);
    }

    @Override
    protected void initEffects() {
        final LastWords lastWords = new LastWords(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final Leader leader = owner.getLeader();
                leader.summon(Arrays.asList(new HolyflameTiger(leader), new HolyflameTiger(leader)));
            }
        };
        effects.add(lastWords);
    }

    @Override
    public String getName() {
        return "Dual Flames";
    }
}
