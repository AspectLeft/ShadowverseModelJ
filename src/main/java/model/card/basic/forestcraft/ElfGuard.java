package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Fanfare;
import model.effect.Ward;
import model.exception.GameEndingException;

public class ElfGuard extends Follower {
    public ElfGuard(Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.FORESTCRAFT, 2,
                1, 3,
                3, 5);
    }

    @Override
    protected void initEffects() {
        Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                if (owner.getLeader().getCountOfCardsPlayedThisTurn() - 1 >= 2) {
                    report(null);
                    final Follower owner = (Follower) getOwner();

                    owner.attachAndSettleIncrementalBuff(1, 1);

                    owner.attachEffect(new Ward(owner));
                }
            }
        };
        this.unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Elf Guard";
    }
}
