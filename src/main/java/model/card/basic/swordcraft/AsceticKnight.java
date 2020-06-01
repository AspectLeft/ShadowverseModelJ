package model.card.basic.swordcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.Trait;
import model.card.basic.swordcraft.token.HeavyKnight;
import model.effect.Fanfare;
import model.exception.GameEndingException;

public final class AsceticKnight extends Follower {

    public AsceticKnight(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SWORDCRAFT, 3,
                1, 2,
                3, 4);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                owner.getLeader().summon(new HeavyKnight(owner.getLeader()));
            }
        };
        this.unevolvedEffects.add(fanfare);
    }

    @Override
    protected void initTraits() {
        addTraits(Trait.OFFICER);
    }

    @Override
    public String getName() {
        return "Ascetic Knight";
    }
}
