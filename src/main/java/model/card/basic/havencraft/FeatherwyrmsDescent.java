package model.card.basic.havencraft;

import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.amulet.CountdownAmulet;
import model.card.basic.havencraft.token.HolywingDragon;
import model.effect.LastWords;
import model.exception.GameEndingException;

public final class FeatherwyrmsDescent extends CountdownAmulet {
    public FeatherwyrmsDescent(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.HAVENCRAFT, 3, 3);
    }

    @Override
    protected void initEffects() {
        final LastWords lastWords = new LastWords(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final HolywingDragon holywingDragon = new HolywingDragon(owner.getLeader());
                owner.getLeader().summon(holywingDragon);
            }
        };
        effects.add(lastWords);
    }

    @Override
    public String getName() {
        return "Featherwyrm's Descent";
    }
}
