package model.card.basic.havencraft;

import model.Leader;
import model.card.AreaTakingCard;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.amulet.CountdownAmulet;
import model.effect.Fanfare;
import model.exception.GameEndingException;

public final class GreaterPriestess extends Follower {
    public GreaterPriestess(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.HAVENCRAFT, 5,
                3, 4,
                5, 6);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                final AreaTakingCard[] area = owner.getLeader().getArea();
                for (int i = 0; i < 5; ++i) {
                    if (area[i] == null) return;
                    if (area[i] instanceof CountdownAmulet) {
                        ((CountdownAmulet)area[i]).decrement(true);
                    }
                }
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Greater Priestess";
    }
}
