package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Fanfare;
import model.exception.GameEndingException;

public class SpartoiSergeant extends Follower {
    public SpartoiSergeant(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SHADOWCRAFT, 2,
                2, 2,
                4, 4);
    }

    @Override
    protected void initEffects() {
        Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                owner.getLeader().increaseShadow(1);
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Spartoi Sergeant";
    }
}
