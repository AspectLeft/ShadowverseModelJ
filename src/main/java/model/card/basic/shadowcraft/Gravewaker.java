package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.shadowcraft.token.Zombie;
import model.effect.LastWords;
import model.exception.GameEndingException;

public final class Gravewaker extends Follower {
    public Gravewaker(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SHADOWCRAFT, 5,
                3, 3,
                5, 5);
    }

    @Override
    protected void initEffects() {
        final LastWords lastWords = new LastWords(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                owner.getLeader().summon(new Zombie(owner.getLeader()));
            }
        };
        unevolvedEffects.add(lastWords);
        evolvedEffects.add(lastWords);
    }

    @Override
    public String getName() {
        return "Gravewaker";
    }
}
