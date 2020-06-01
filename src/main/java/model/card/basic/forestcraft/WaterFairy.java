package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.forestcraft.token.Fairy;
import model.effect.LastWords;
import model.exception.GameEndingException;

public final class WaterFairy extends Follower {

    public WaterFairy(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.FORESTCRAFT, 1,
                1, 1,
                3, 3);
    }

    @Override
    protected void initEffects() {
        final LastWords lastWords = new LastWords(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                owner.getLeader().putCardIntoHand(new Fairy(owner.getLeader()));
            }
        };
        this.unevolvedEffects.add(lastWords);
        this.evolvedEffects.add(lastWords);
    }

    @Override
    public String getName() {
        return "Water Fairy";
    }
}
