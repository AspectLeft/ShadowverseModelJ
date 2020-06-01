package model.card;

import lombok.Getter;
import model.Entity;
import model.Game;
import model.Leader;
import model.effect.Choice;
import model.effect.cost.CostOperator;
import model.event.PlayCardEvent;
import model.exception.GameEndingException;

import java.util.*;

public abstract class CardBase extends Entity {
    @Getter
    protected final Leader leader;

    protected final Rarity rarity;

    protected final CardClass defaultCardClass;

    protected final int defaultCost;

    protected CardClass cardClass;
    protected int cost;
    protected final List<CostOperator> costOperators = new ArrayList<>();
    protected final Set<Trait> traits = new HashSet<>();

    // Not allowed constructor
    protected CardBase() {
        super();
        this.leader = null;
        this.rarity = Rarity.BRONZE;
        this.defaultCardClass = CardClass.NEUTRAL;
        this.defaultCost = 0;
        this.cardClass = this.defaultCardClass;
        this.cost = 0;
    }

    protected CardBase(final Leader leader, final Rarity rarity, final CardClass defaultCardClass, final int defaultCost) {
        super();
        this.leader = leader;
        this.rarity = rarity;
        this.defaultCardClass = defaultCardClass;
        this.defaultCost = defaultCost;
        this.cardClass = this.defaultCardClass;
        this.cost = this.defaultCost;
        initTraits();
    }

    public void reset() {
        this.cardClass = this.defaultCardClass;
        this.cost = this.defaultCost;
        traits.clear();
        initTraits();
    }

    public void addTraits(Trait... traits) {
        this.traits.addAll(Arrays.asList(traits));
    }

    public abstract String getName();

    /**
     * Trigger Order:
     * 1. Fanfare/ Spell effect
     * 2. PlayCardEvent
     * 3. AreaTakingCardComesIntoPlayEvent
     * 4. settle
     *
     * Different from the GUI, which show fanfare animation and sound when card is put in area
     *
     * @param options indices of choices
     * @throws GameEndingException you can end game by playing a card
     */
    public void play(final List<Choice> options) throws GameEndingException {
        this.costOperators.clear();
        getGame().triggerEffect(new PlayCardEvent(this));
    }

    public Game getGame() {
        return leader != null ? leader.getGame() : null;
    }

    public void startTurn() {}

    public int getCost() {
        return this.costOperators.stream().reduce(i -> i, (op1, op2) -> i -> op2.applyAsInt(op1.applyAsInt(i)))
                .applyAsInt(defaultCost);
    }

    public List<List<Choice>> getChoices() {
        return null;
    }

    protected void initTraits() {}

    public boolean hasTrait(final Trait trait) {
        return traits.contains(trait);
    }
}
