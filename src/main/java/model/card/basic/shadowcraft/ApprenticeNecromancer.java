package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.shadowcraft.token.Zombie;
import model.effect.Fanfare;
import model.event.NecromancyPerformedEvent;
import model.exception.GameEndingException;

public class ApprenticeNecromancer extends Follower {
    public ApprenticeNecromancer(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SHADOWCRAFT, 3,
                2, 3,
                4, 5);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public void settle() throws GameEndingException {
                final Leader leader = owner.getLeader();
                final int necromancyValue = 4;
                if (leader.getCountOfShadow() >= necromancyValue) {
                    report(null);
                    leader.decreaseShadow(4);
                    leader.summon(new Zombie(leader));
                    leader.getGame().triggerEffect(new NecromancyPerformedEvent(leader, necromancyValue));
                }
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Apprentice Necromancer";
    }
}
