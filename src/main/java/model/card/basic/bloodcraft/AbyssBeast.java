package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Follower;
import model.card.Rarity;
import model.effect.Choice;
import model.effect.Fanfare;
import model.exception.GameEndingException;

import java.util.Collections;
import java.util.List;

public class AbyssBeast extends Follower {
    public AbyssBeast(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.BLOODCRAFT, 7,
                5, 6,
                7, 8);
    }

    @Override
    protected void initEffects() {
        final Fanfare fanfare = new Fanfare(this) {
            @Override
            public List<List<Choice>> getChoices() {
                if (!owner.getLeader().isVengeance()) return null;
                return Collections.singletonList(owner.getLeader().getEnemyEffectTargetFollowerChoices());
            }

            @Override
            public void settle() throws GameEndingException {
                if (options == null || options.isEmpty()) return;
                report(null);
                effectDamage(5, owner.getLeader().getOpponent().getArea()[options.get(0).getValue()]);
            }
        };
        unevolvedEffects.add(fanfare);
    }

    @Override
    public String getName() {
        return "Abyss Beast";
    }
}
