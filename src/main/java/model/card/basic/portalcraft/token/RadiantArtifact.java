package model.card.basic.portalcraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.Trait;
import model.effect.LastWords;
import model.effect.Storm;
import model.exception.GameEndingException;

public final class RadiantArtifact extends Follower {
    public RadiantArtifact(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.PORTALCRAFT, 5,
                4, 3,
                6, 5);
    }

    @Override
    protected void initTraits() {
        addTraits(Trait.ARTIFACT);
    }

    @Override
    protected void initEffects() {
        final Storm storm = new Storm(this);

        unevolvedEffects.add(storm);
        evolvedEffects.add(storm);

        final LastWords lastWords = new LastWords(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                if (owner.getLeader().isTurn()) {
                    owner.getLeader().retrieveOneCardFromDeck(card -> card.hasTrait(Trait.ARTIFACT));
                }
                else {
                    owner.getLeader().drawCard(1);
                }
            }
        };

        unevolvedEffects.add(lastWords);
        evolvedEffects.add(lastWords);
    }

    @Override
    public String getName() {
        return "Radiant Artifact";
    }
}
