package model.card;

public class Spell extends CardBase {
    private Spell() {
        super(Rarity.BRONZE, CardClass.NEUTRAL,0);
    }

    protected Spell(final Rarity rarity, final CardClass defaultCardClass, final int defaultCost) {
        super(rarity, defaultCardClass, defaultCost);
    }
}
