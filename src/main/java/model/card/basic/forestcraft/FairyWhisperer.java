package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.forestcraft.token.Fairy;
import model.effect.Fanfare;
import model.exception.GameEndingException;

import java.util.Arrays;

public final class FairyWhisperer extends Follower {


    public FairyWhisperer(Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.FORESTCRAFT, 2,
                1, 1,
                2, 2);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final Leader leader = owner.getLeader();
                leader.putCardsIntoHand(Arrays.asList(new Fairy(leader), new Fairy(leader)));
            }
        };
        this.unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Fairy Whisperer";
    }
}
