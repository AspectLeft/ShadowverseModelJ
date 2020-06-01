package model.card.basic.neutral;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Fanfare;
import model.exception.GameEndingException;

public class SammyWizardsApprentice extends Follower {
    public SammyWizardsApprentice(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.NEUTRAL, 2,
                2, 2,
                4, 4);
    }

    @Override
    protected void initEffects() {
        Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                getOwner().getLeader().drawCard(1);
                getOwner().getLeader().getOpponent().drawCard(1);
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Sammy, Wizard's Apprentice";
    }
}
