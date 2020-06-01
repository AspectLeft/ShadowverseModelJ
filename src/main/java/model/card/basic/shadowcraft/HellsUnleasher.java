package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.shadowcraft.token.Lich;
import model.effect.LastWords;
import model.exception.GameEndingException;

public final class HellsUnleasher extends Follower {
    public HellsUnleasher(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.SHADOWCRAFT, 4,
                1, 1,
                3, 3);
    }

    @Override
    protected void initEffects() {
        final LastWords lastWords = new LastWords(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                owner.getLeader().summon(new Lich(owner.getLeader()));
            }
        };
        unevolvedEffects.add(lastWords);
        evolvedEffects.add(lastWords);
    }

    @Override
    public String getName() {
        return "Hell's Unleasher";
    }
}
