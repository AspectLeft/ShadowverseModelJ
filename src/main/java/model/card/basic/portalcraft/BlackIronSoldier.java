package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.Trait;
import model.effect.Fanfare;
import model.exception.GameEndingException;

public class BlackIronSoldier extends Follower {
    public BlackIronSoldier(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.PORTALCRAFT, 6,
                5, 6,
                7, 8);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                owner.getLeader().retrieveOneCardFromDeck(card -> card.hasTrait(Trait.ARTIFACT));
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Black Iron Soldier";
    }
}
