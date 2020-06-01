package model.card.basic.shadowcraft.token;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;

public final class Zombie extends Follower {
    public Zombie(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SHADOWCRAFT, 2,
                2, 2,
                4, 4);
    }

    @Override
    public String getName() {
        return "Zombie";
    }
}
