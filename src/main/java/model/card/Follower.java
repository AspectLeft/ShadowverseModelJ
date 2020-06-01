package model.card;

public abstract class Follower extends CardBase {
    protected final int defaultUnevolvedAttack;
    protected final int defaultUnevolvedDefense;
    protected final int defaultEvolvedAttack;
    protected final int defaultEvolvedDefense;

    private Follower() {
        super(Rarity.BRONZE, CardClass.NEUTRAL,0);
        this.defaultUnevolvedAttack = 0;
        this.defaultUnevolvedDefense = 0;
        this.defaultEvolvedAttack = 0;
        this.defaultEvolvedDefense = 0;
    }

    protected Follower(final Rarity rarity, final CardClass defaultCardClass, final int defaultCost,
                    final int defaultUnevolvedAttack, final int defaultUnevolvedDefense,
                    final int defaultEvolvedAttack, final int defaultEvolvedDefense) {
        super(rarity, defaultCardClass, defaultCost);
        this.defaultUnevolvedAttack = defaultUnevolvedAttack;
        this.defaultUnevolvedDefense = defaultUnevolvedDefense;
        this.defaultEvolvedAttack = defaultEvolvedAttack;
        this.defaultEvolvedDefense = defaultEvolvedDefense;
    }
}
