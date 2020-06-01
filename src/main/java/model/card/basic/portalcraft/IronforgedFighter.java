package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.portalcraft.token.RadiantArtifact;
import model.effect.Fanfare;
import model.exception.GameEndingException;

import java.util.Arrays;

public final class IronforgedFighter extends Follower {
    public IronforgedFighter(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.PORTALCRAFT, 4,
                4, 3,
                6, 5);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final Leader leader = owner.getLeader();
                leader.putCardsIntoDeck(Arrays.asList(new RadiantArtifact(leader), new RadiantArtifact(leader)));
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Ironforged Fighter";
    }
}
