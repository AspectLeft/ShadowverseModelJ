package model.card.basic.havencraft;

import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.amulet.CountdownAmulet;
import model.card.basic.havencraft.token.HolyflameTiger;
import model.effect.LastWords;
import model.exception.GameEndingException;

public final class BeastlyVow extends CountdownAmulet {
    public BeastlyVow(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.HAVENCRAFT, 2, 2);
    }

    @Override
    protected void initEffects() {
        final LastWords lastWords = new LastWords(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final HolyflameTiger holyflameTiger = new HolyflameTiger(owner.getLeader());
                owner.getLeader().summon(holyflameTiger);
            }
        };
        effects.add(lastWords);
    }

    @Override
    public String getName() {
        return "Beastly Vow";
    }
}
