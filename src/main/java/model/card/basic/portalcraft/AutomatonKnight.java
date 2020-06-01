package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.portalcraft.token.Puppet;
import model.effect.LastWords;
import model.exception.GameEndingException;

public class AutomatonKnight extends Follower {
    public AutomatonKnight(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.PORTALCRAFT, 3,
                3, 2,
                5, 4);
    }

    @Override
    protected void initEffects() {
        final LastWords lastWords = new LastWords(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                owner.getLeader().putCardIntoHand(new Puppet(owner.getLeader()));
            }
        };
        unevolvedEffects.add(lastWords);
        evolvedEffects.add(lastWords);
    }

    @Override
    public String getName() {
        return "Automaton Knight";
    }
}
