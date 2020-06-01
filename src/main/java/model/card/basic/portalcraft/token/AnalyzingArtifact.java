package model.card.basic.portalcraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.Trait;
import model.effect.LastWords;
import model.exception.GameEndingException;

public final class AnalyzingArtifact extends Follower {
    public AnalyzingArtifact(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.PORTALCRAFT, 1,
                2, 1,
                4, 3);
    }

    @Override
    protected void initEffects() {
        final LastWords lastWords = new LastWords(this) {
            @Override
            public void settle() throws GameEndingException {
                owner.getLeader().drawCard(1);
            }
        };
        unevolvedEffects.add(lastWords);
        evolvedEffects.add(lastWords);
    }

    @Override
    protected void initTraits() {
        addTraits(Trait.ARTIFACT);
    }

    @Override
    public String getName() {
        return "Analyzing Artifact";
    }
}
