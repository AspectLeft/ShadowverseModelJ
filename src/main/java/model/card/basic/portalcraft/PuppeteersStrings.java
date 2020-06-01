package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.card.basic.portalcraft.token.Puppet;
import model.card.spell.Spell;
import model.effect.EffectWithOptions;
import model.exception.GameEndingException;
import util.CardCopyUtil;

public class PuppeteersStrings extends Spell {
    public PuppeteersStrings(final Leader leader) {
        super(leader, Rarity.SILVER, CardClass.PORTALCRAFT, 4, new EffectWithOptions(leader) {
            @Override
            public void settle() throws GameEndingException {
                owner.getLeader().spellDamage(1, owner.getLeader().getOpponent().getAlliedFollowers());
                owner.getLeader().putCardsIntoHand(CardCopyUtil.createNCardInstances(3, owner.getLeader(),
                        Puppet.class));
            }
        });
    }

    @Override
    public String getName() {
        return "Puppeteer's Strings";
    }
}
