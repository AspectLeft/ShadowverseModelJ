package model.card.basic.swordcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.Trait;
import model.card.basic.swordcraft.token.Knight;
import model.effect.Fanfare;
import model.exception.GameEndingException;

public final class OathlessKnight extends Follower {
    public OathlessKnight(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SWORDCRAFT, 2,
                1, 1,
                3, 3);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                owner.getLeader().summon(new Knight(owner.getLeader()));
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
        return "Oathless Knight";
    }
}
