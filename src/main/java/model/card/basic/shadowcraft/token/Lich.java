package model.card.basic.shadowcraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;

public final class Lich extends Follower {
    public Lich(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SHADOWCRAFT, 4,
                4, 4,
                6, 6);
    }

    @Override
    public String getName() {
        return "Lich";
    }
}
