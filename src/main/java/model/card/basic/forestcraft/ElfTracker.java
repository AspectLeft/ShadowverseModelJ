package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Fanfare;
import model.exception.GameEndingException;
import util.RandomUtil;

public final class ElfTracker extends Follower {
    public ElfTracker(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.FORESTCRAFT, 6,
                4, 5,
                6, 7);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                report(null);
                for (int i = 0; i < 2; ++i) {
                    effectDamage(1, RandomUtil.pickOne(owner.getLeader().getRandom(),
                            owner.getLeader().getOpponent().getAlliedFollowers()));
                }
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Elf Tracker";
    }
}
