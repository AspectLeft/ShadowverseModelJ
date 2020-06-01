package model.card.basic.swordcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.Trait;
import model.effect.Ambush;

public final class KunoichiTrainee extends Follower {
    public KunoichiTrainee(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SWORDCRAFT, 2,
                2, 1,
                4, 3);
    }

    @Override
    protected void initEffects() {
        final Ambush ambush = new Ambush(this);
        unevolvedEffects.add(ambush);
        evolvedEffects.add(ambush);
    }

    @Override
    protected void initTraits() {
        addTraits(Trait.OFFICER);
    }

    @Override
    public String getName() {
        return "Kunoichi Trainee";
    }
}
