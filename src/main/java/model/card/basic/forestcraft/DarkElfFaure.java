package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.forestcraft.token.Fairy;
import model.effect.StrikeEffect;
import model.exception.GameEndingException;

public final class DarkElfFaure extends Follower {
    public DarkElfFaure(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.FORESTCRAFT, 3,
                2, 3,
                4, 5);
    }

    @Override
    protected void initEffects() {
        final StrikeEffect strikeEffect = new StrikeEffect(this) {
            @Override
            public void settle() throws GameEndingException {
                if (!((Follower) owner).isInArea()) {
                    return;
                }
                report(null);
                final Leader leader = owner.getLeader();
                leader.putCardIntoHand(new Fairy(leader));
            }
        };
        unevolvedEffects.add(strikeEffect);
        evolvedEffects.add(strikeEffect);
    }

    @Override
    public String getName() {
        return "Dark Elf Faure";
    }
}
