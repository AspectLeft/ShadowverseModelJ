package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.card.spell.EnemyFollowerTargetingSpell;
import model.effect.EffectWithOptions;
import model.event.NecromancyPerformedEvent;
import model.exception.GameEndingException;

public class UndyingResentment extends EnemyFollowerTargetingSpell {
    public UndyingResentment(final Leader leader) {
        super(leader, Rarity.BRONZE, CardClass.SHADOWCRAFT, 2, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                final Leader leader = (Leader) getOwner();
                final Follower target = (Follower) leader.getOpponent().getArea()[options.get(0).getValue()];
                final int necromancyValue = 2;
                if (leader.getCountOfShadow() < necromancyValue) {
                    leader.spellDamage(3, target);
                }
                else {
                    leader.decreaseShadow(necromancyValue);
                    leader.spellDamage(5, target);
                    leader.getGame().triggerEffect(new NecromancyPerformedEvent(leader, necromancyValue));
                }
            }
        });
    }

    @Override
    public String getName() {
        return "Undying Resentment";
    }
}
