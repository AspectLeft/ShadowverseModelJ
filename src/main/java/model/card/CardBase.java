package model.card;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class CardBase {
    protected final Rarity rarity;

    protected final CardClass defaultCardClass;

    protected final int defaultCost;

    protected CardClass cardClass;
    protected int cost;
    protected final Set<Trait> traits = new HashSet<>();

    // Not allowed constructor
    private CardBase() {
        super();
        this.rarity = Rarity.BRONZE;
        this.defaultCardClass = CardClass.NEUTRAL;
        this.defaultCost = 0;
    }

    protected CardBase(final Rarity rarity, final CardClass defaultCardClass, final int defaultCost) {
        super();
        this.rarity = rarity;
        this.defaultCardClass = defaultCardClass;
        this.defaultCost = defaultCost;
    }

    public void addTraits(Trait... traits) {
        this.traits.addAll(Arrays.asList(traits));
    }
}
