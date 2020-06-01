package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.basic.shadowcraft.token.Lich;
import model.card.spell.EnemyFollowerTargetingSpell;
import model.effect.EffectWithOptions;
import model.event.NecromancyPerformedEvent;
import model.exception.GameEndingException;

public final class CallOfTheVoid extends EnemyFollowerTargetingSpell {
    public CallOfTheVoid(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SHADOWCRAFT, 4, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                final Leader leader = owner.getLeader();
                final Follower target = (Follower) leader.getOpponent().getArea()[options.get(0).getValue()];

                final int necromancy = 4;
                final boolean enablingNecromancy = leader.getCountOfShadow() >= necromancy;
                if (enablingNecromancy) {
                    leader.getGame().triggerEffect(new NecromancyPerformedEvent(owner, necromancy));
                }
                report(null);
                target.tryDestroy();
                if (enablingNecromancy) {
                    leader.decreaseShadow(necromancy);
                    leader.summon(new Lich(leader));
                }
            }
        });
    }

    @Override
    public String getName() {
        return "Call of the Void";
    }
}
