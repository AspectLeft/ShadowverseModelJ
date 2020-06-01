package model.card;

public abstract class Amulet extends CardBase {
    private Amulet() {
        super(Rarity.BRONZE, CardClass.NEUTRAL,0);
    }

    protected Amulet(final Rarity rarity, final CardClass defaultCardClass, final int defaultCost) {
        super(rarity, defaultCardClass, defaultCost);
    }
}
