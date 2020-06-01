package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.portalcraft.token.AnalyzingArtifact;
import model.effect.Fanfare;
import model.exception.GameEndingException;

import java.util.Arrays;

public final class MagisteelLion extends Follower {
    public MagisteelLion(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.PORTALCRAFT, 2,
                2, 2,
                4, 4);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final Leader leader = owner.getLeader();
                leader.putCardsIntoDeck(Arrays.asList(new AnalyzingArtifact(leader), new AnalyzingArtifact(leader)));
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Magisteel Lion";
    }
}
