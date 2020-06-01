package model;

import lombok.Getter;
import lombok.Setter;
import model.card.AreaTakingCard;
import model.card.CardBase;
import model.card.CardClass;
import model.card.Follower;
import model.card.amulet.CountdownAmulet;
import model.effect.Choice;
import model.effect.ChoiceType;
import model.effect.EffectBase;
import model.event.*;
import model.exception.DataInconsistentException;
import model.exception.GameEndingException;
import model.report.Report;
import util.CardCopyUtil;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// TODO: incomplete
public class Leader extends Entity {
    public static final int DEFAULT_MAXIMUM_DEFENSE = 20;
    public static final int MAXIMUM_EP_FIRST = 2;
    public static final int MAXIMUM_EP_SECOND = 3;
    public static final int MAXIMUM_HAND_CARD = 9;

    @Getter private final int id;
    @Getter private final Game game;
    @Getter private int maximumDefense = DEFAULT_MAXIMUM_DEFENSE;
    @Getter @Setter private int defense = DEFAULT_MAXIMUM_DEFENSE;
    @Getter private int maximumPp = 0;
    @Getter private int pp = 0;
    @Getter private int countOfDrewCardsThisTurn = 0;
    @Getter private int countOfCardsPlayedThisTurn = 0;
    @Getter private int countOfShadow = 0;
    private int countOfCardsToDrawOnTurnStart = 1;
    @Getter private int numberOfTurn = 0;
    @Getter private final boolean isFirst;
    @Getter private final int maximumEp;
    @Getter private int ep;
    @Getter private final CardClass leaderClass;
    private  LinkedList<CardBase> deck = new LinkedList<>();
    @Getter private ArrayList<CardBase> hand = new ArrayList<>();
    @Getter private AreaTakingCard[] area = new AreaTakingCard[5];
    private ArrayList<EffectBase> effects = new ArrayList<>();
    private Map<String, Integer> records = new HashMap<>();

    Leader(final Game game, final int id, final CardClass leaderClass, final boolean isFirst,
           final Collection<Class<? extends CardBase>> deck) throws DataInconsistentException {
        this.game = game;
        this.id = id;
        this.leaderClass = leaderClass;
        this.isFirst = isFirst;
        this.ep = this.maximumEp = isFirst ? MAXIMUM_EP_FIRST : MAXIMUM_EP_SECOND;
        this.deck.addAll(deck.stream()
                .map(cardClass -> CardCopyUtil.createCardInstance(this, cardClass))
                .collect(Collectors.toList())
        );
        Collections.shuffle(this.deck);
    }

    private void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    public void startTurn() throws GameEndingException {
        this.numberOfTurn++;
        // increment max pp
        if (this.maximumPp < 10) {
            this.maximumPp++;
            this.game.triggerEffect(new MaximumPpChangeEvent(this, maximumPp - 1, maximumPp));
        }

        // TODO: Yuzuki
        this.pp = maximumPp;

        for (CardBase card: area) {
            if (card != null) {
                // Flush attacked state
                card.startTurn();
            }
        }

        this.game.triggerEffect(new TurnStartEvent(this));

        if (!isFirst && this.numberOfTurn == 1) {
            drawCard(countOfCardsToDrawOnTurnStart + 1);
        }
        else {
            drawCard(countOfCardsToDrawOnTurnStart);
        }

        this.game.settleAllEffects();
    }

    public void drawCard(final int count) throws GameEndingException {
        final List<CardBase> cardsDrew = removeCardsFromDeck(count);
        if (cardsDrew.size() < count) {
            // TODO: Spartacus
            throw new GameEndingException(this, false, "Draw from empty deck");
        }
        countOfDrewCardsThisTurn += cardsDrew.size();
        for (final CardBase card: cardsDrew) {
            this.game.triggerEffect(new CardDrewEvent(card));
        }

        putCardsIntoHand(cardsDrew);
    }

    public void putCardIntoHand(final CardBase card) throws GameEndingException {
        putCardsIntoHand(Collections.singletonList(card));
    }

    public void putCardsIntoHand(final List<CardBase> cardList) throws GameEndingException{
        final int prevHandSize = getHandSize();
        if (prevHandSize + cardList.size() >= MAXIMUM_HAND_CARD) {
            final List<CardBase> cardsToDiscard = cardList.subList(MAXIMUM_HAND_CARD - getHandSize(),
                    cardList.size());
            this.increaseShadow(cardsToDiscard.size());
            cardsToDiscard.clear();
        }

        addCardsToHand(cardList);
        report(Report.builder().type(Report.ReportType.CARD_INTO_HAND)
                .id(id).values(new int[]{cardList.size()}).build());
        for (final CardBase card: cardList) {
            this.game.triggerEffect(new CardAddedToHandEvent(card));
        }
        // this.game.settleAllEffects();
        this.game.triggerEffect(new HandSizeChangeEvent(this, prevHandSize, getHandSize()));
    }

    public void putCardsIntoDeck(final List<CardBase> cardList) {
        this.deck.addAll(cardList);
        shuffleDeck();
        cardList.stream().collect(Collectors.groupingBy(CardBase::getName)).forEach((key, value) ->
                report(Report.builder().type(Report.ReportType.CARDS_INTO_DECK)
                        .id(id).name(key).values(new int[]{value.size()}).build()));
        //TODO: resonance change event
    }

    public CardBase playCardFromHand(final int index, final List<Choice> options) throws GameEndingException {
        assert 0 <= index;
        assert index < getHandSize();
        final CardBase card = this.hand.get(index);
        this.hand.remove(index);
        playCard(card, options);
        return card;
    }

    public void playCard(CardBase card, final List<Choice> options) throws GameEndingException {
        // TODO: assert playable
        usePp(card.getCost());
        increaseCountOfCardPlayedThisTurn(1);
        card.play(options);
        game.settleAllEffects();
    }

    // By hand
    public void evolve(final int areaIndex, final List<Choice> options) throws DataInconsistentException,
            GameEndingException {
        // TODO: assert can evolve
        try {
            Follower follower = (Follower) this.area[areaIndex];
            follower.evolve(options);
            if (! follower.freeToEvolve()) {
                this.ep--;
            }
            game.settleAllEffects();
        } catch (ArrayIndexOutOfBoundsException | ClassCastException e) {
            throw new DataInconsistentException("Illegal area index");
        }
    }

    public void endTurn() throws GameEndingException {
        this.game.triggerAndSettle(new TurnEndEvent(this));
        this.countOfCardsPlayedThisTurn = 0;
        this.countOfDrewCardsThisTurn = 0;
        this.game.endTurn();
    }

    public void increaseShadow(final int count) {
        report(Report.builder().type(Report.ReportType.INCREASE_SHADOW).id(id).values(new int[]{count}).build());
        this.countOfShadow += count;
    }

    public void decreaseShadow(final int delta) {
        this.countOfShadow -= delta;
    }

    public void usePp(final int count) {
        assert count <= getPp();
        this.pp -= count;
    }

    public void takeDamage(final int damage) throws GameEndingException {
        assert damage >= 0;
        this.defense -= damage;
        if (this.defense <= 0) {
            throw new GameEndingException(this, false, "Defense below 1");
        }
    }

    /**
     *
     * @param count numbers of cards
     * @return the removed cards. If the remaining cards are less than count, will
     */
    public List<CardBase> removeCardsFromDeck(final int count) {
        assert count > 0;
        final List<CardBase> result = new LinkedList<>();
        while (!this.deck.isEmpty() && result.size() < count) {
            result.add(this.deck.removeLast());
        }
        return result;
    }

    private void addCardsToHand(final Collection<CardBase> cards) {
        this.hand.addAll(cards);
        if (getHandSize() >= MAXIMUM_HAND_CARD) {
            this.hand.subList(MAXIMUM_HAND_CARD, getHandSize()).clear();
        }
    }

    public void drawCardsOnStart(final int count) {
        addCardsToHand(removeCardsFromDeck(count));
    }

    public void addCardsToDeckWithShuffle(final Collection<CardBase> cards) {
        this.deck.addAll(cards);
        shuffleDeck();
    }

    public void changeCardsOnStart(final List<Boolean> changeFlags) throws GameEndingException {
        final Iterator<Boolean> iterator = changeFlags.iterator();
        final LinkedList<CardBase> discardedCards = new LinkedList<>();
        final Iterator<CardBase> handIterator = this.hand.iterator();
        int count = 0;
        while (handIterator.hasNext()) {
            if (iterator.next()) {
                discardedCards.add(handIterator.next());
                handIterator.remove();
                count++;
            }
            else {
                handIterator.next();
            }
        }
        addCardsToDeckWithShuffle(discardedCards);
        if (count != 0) {
            drawCardsOnStart(count);
        }
        report(Report.builder().type(Report.ReportType.CHANGE_CARD_ON_START)
                .id(id).values(new int[]{count}).build());
        this.game.finishedChangeCardsOnStart();
    }

    public boolean cardIsInArea(final CardBase card) {
        for (CardBase areaCard: getArea()) {
            if (areaCard == card) return true;
        }
        return false;
    }

    public int getHandSize() {
        return this.hand.size();
    }

    public int getDeckSize() {
        return this.deck.size();
    }

    public void addCardToArea(final AreaTakingCard cardBase) {
        for (int index = 0; index < 5; index++) {
            if (area[index] == null) {
                area[index] = cardBase;
                return;
            }
        }
    }

    public ArrayList<EffectBase> getEffects() {
        return effects;
    }

    public void followerStrike(final int followerIndex, final Entity target) throws GameEndingException, DataInconsistentException {
        // TODO assert can attack
        Follower follower = (Follower) this.area[followerIndex];
        follower.strike(target);
        game.settleAllEffects();
    }

    public void removeCardFromArea(CardBase cardBase) {
        for (int i = 0; i < 5; i++) {
            if (cardBase == area[i]) {
                area[i] = null;
                break;
            }
        }
        // Bubbling
        for (int i = 0, j; i < 5; ++i) {
            if (area[i] == null) continue;
            j = i;
            while (j > 1 && area[j - 1] == null) {
                area[j - 1] = area[j];
                area[j] = null;
                j--;
            }
        }
    }

    public Leader getOpponent() {
        return game.getCounterpart(this);
    }

    public void spellDamage(final int damageValue, final Entity target) throws GameEndingException {
        giveDamage(DamageType.SPELL, damageValue, target);
    }

    public void spellDamage(final int damageValue, final List<? extends Entity> targetList) {
        giveDamage(DamageType.SPELL, damageValue, targetList);
    }

    public boolean areaIsFull() {
        return this.area[4] != null;
    }

    public void summon(final AreaTakingCard card) {
        if (areaIsFull()) {
            report(Report.builder().type(Report.ReportType.AREA_OVERFLOW).id(id)
                    .name(card.getName()).build());
            return;
        }
        card.comesIntoPlay();
    }

    // Ordered
    public void summon(final List<? extends AreaTakingCard> cardList) {
        cardList.forEach(this::summon);
    }

    public List<Follower> getAlliedFollowers() {
        final List<Follower> alliedFollowers = new ArrayList<>(5);
        for (final AreaTakingCard card: this.area) {
            if (card instanceof Follower) {
                alliedFollowers.add((Follower)card);
            }
        }
        return alliedFollowers;
    }

    public List<Entity> getAlliedFollowersAndLeader() {
        final List<Entity> list = new ArrayList<>(6);
        list.addAll(getAlliedFollowers());
        list.add(this);
        return list;
    }

    public List<CountdownAmulet> getAlliedCountdownAmulets() {
        final List<CountdownAmulet> alliedCountdownAmulets = new ArrayList<>(5);
        for (final AreaTakingCard card: this.area) {
            if (card instanceof CountdownAmulet) {
                alliedCountdownAmulets.add((CountdownAmulet) card);
            }
        }
        return alliedCountdownAmulets;
    }

    @Override
    public Leader getLeader() {
        return this;
    }

    @Override
    public String getName() {
        return isFirst ? "1" : "0";
    }

    public Random getRandom() {
        return this.game.getRandom();
    }

    public void recoverPp(final int value) {
        this.pp = Math.min(this.maximumPp, this.pp + value);
    }

    public void increaseCountOfCardPlayedThisTurn(final int delta) {
        assert delta > 0;
        countOfCardsPlayedThisTurn += delta;
    }

    public List<Integer> getStrikeTargetIndices() {
        int maxPriority = 0;
        final List<Integer> targetIndices = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            if (area[i] == null) break;
            if (area[i] instanceof Follower) {
                final int priority = ((Follower) area[i]).getTargetPriority();
                if (priority < maxPriority) continue;
                if (priority > maxPriority) {
                    targetIndices.clear();
                    maxPriority = priority;
                }
                targetIndices.add(i);
            }
        }
        return targetIndices;
    }

    private int collectEffectTargetFollowerIndices(final List<Integer> list) {

        return collectEffectTargetWithFilters(list, card -> card instanceof Follower);
    }

    private int collectEffectTargetWithFilters(final List<Integer> list,
                                               final Predicate<AreaTakingCard> filter) {
        int maxPriority = 0;
        for (int i = 0; i < 5; ++i) {
            if (area[i] == null) break;
            if (filter.test(area[i])) {
                final int priority = area[i].getSelectPriority();
                if (priority < maxPriority) continue;
                if (priority > maxPriority) {
                    list.clear();
                    maxPriority = priority;
                }
                list.add(i);
            }
        }
        return maxPriority;
    }

    public List<Integer> getEffectTargetFollowerAndLeaderIndices() {
        final List<Integer> followerIndices = new ArrayList<>();
        int maxPriority = collectEffectTargetFollowerIndices(followerIndices);
        if (maxPriority == 0) {
            // -1 means this leader
            followerIndices.add(-1);
        }
        return followerIndices;
    }

    public List<Choice> getEnemyEffectTargetChoicesWithFilter(final Predicate<AreaTakingCard> filter) {
        return buildChoiceList(ChoiceType.ENEMY_AREA_LEADER, getOpponent().getEffectTargetIndicesWithFilter(filter));
    }

    public List<Choice> getEnemyEffectTargetFollowerChoices() {
        return buildChoiceList(ChoiceType.ENEMY_AREA_LEADER, getOpponent().getEffectTargetFollowerIndices());
    }

    public List<Choice> getEnemyEffectTargetFollowerAndLeaderChoices() {
        return buildChoiceList(ChoiceType.ENEMY_AREA_LEADER, getOpponent().getEffectTargetFollowerAndLeaderIndices());
    }

    private List<Choice> buildChoiceList(final ChoiceType type, final List<Integer> valueList) {
        return valueList.stream().map(value -> Choice.builder()
                .type(ChoiceType.ENEMY_AREA_LEADER).value(value).build()).collect(Collectors.toList());
    }

    public List<Integer> getEffectTargetFollowerIndices() {
        final List<Integer> followerIndices = new ArrayList<>();
        collectEffectTargetFollowerIndices(followerIndices);
        return followerIndices;
    }

    public List<Integer> getEffectTargetIndicesWithFilter(final Predicate<AreaTakingCard> filter) {
        final List<Integer> cardIndices = new ArrayList<>();
        collectEffectTargetWithFilters(cardIndices, filter);
        return cardIndices;
    }

    public List<Integer> getCountdownAmuletIndices() {
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            if (area[i] == null) break;
            if (area[i] instanceof CountdownAmulet) {
                list.add(i);
            }
        }
        return list;
    }

    public List<Choice> getCountdownAmuletChoices() {
        return buildChoiceList(ChoiceType.SELF_AREA_LEADER, getCountdownAmuletIndices());
    }

    public boolean isWarded() {
        for (int i = 0; i < 5; ++i) {
            if (area[i] == null) break;
            if (area[i] instanceof Follower) {
                final int priority = ((Follower) area[i]).getTargetPriority();
                if (priority > 0) return true;
            }
        }
        return false;
    }

    public boolean isOverflow() {
        return this.maximumPp >= 7;
    }

    // God-mode
    // Not limiting to 10
    public void setMaximumPp(final int value) {
        assert 0 <= value;
        this.maximumPp = value;
    }

    public void setMaximumPpAndRecover(final int value) {
        setMaximumPp(value);
        recoverPp(this.maximumPp);
    }

    public void report(final Report report) {
        this.game.getReporter().report(report);
    }

    // The function to call when ramp
    public void incrementMaximumPp() {
        if (this.maximumPp == 10) return;
        this.maximumPp++;
        report(Report.builder().type(Report.ReportType.RAMP).id(id).values(new int[]{1}).build());
        this.game.triggerEffect(new MaximumPpChangeEvent(this, this.maximumPp - 1, this.maximumPp));
    }

    public void decrementMaximumPp() {
        if (this.maximumPp == 0) return;
        this.maximumPp--;
        if (this.pp > this.maximumPp) {
            this.pp = this.maximumPp;
        }
        report(Report.builder().type(Report.ReportType.RAMP).id(id).values(new int[]{-1}).build());
        this.game.triggerEffect(new MaximumPpChangeEvent(this, this.maximumPp + 1, this.maximumPp));
    }

    public boolean isVengeance() {
        //TODO: moon
        return this.defense <= 10;
    }

    public boolean isResonance() {
        return this.deck.size() % 2 == 0;
    }

    public void heal(final int healValue) {
        this.defense = Math.min(this.maximumDefense, this.defense + healValue);
        report(Report.builder().type(Report.ReportType.HEAL).id(id).values(new int[]{healValue}).build());
    }

    public AreaTakingCard findAreaInstance(Class<? extends AreaTakingCard> cardClass) {
        for (int i = 0; i < 5; ++i) {
            if (area[i] == null) return null;
            if (cardClass.isInstance(area[i])) {
                return area[i];
            }
        }
        return null;
    }

    public List<Integer> getAlliedFollowerIndices() {
        final List<Integer> list = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            if (area[i] == null) return list;
            if (area[i] instanceof Follower) {
                list.add(i);
            }
        }
        return list;
    }

    public int getRecord(final String key) {
        return records.getOrDefault(key, 0);
    }

    public void setRecord(final String key, final int value) {
        records.put(key, value);
    }

    public void incrementRecord(final String key) {
        records.put(key, records.getOrDefault(key, 0) + 1);
    }

    public List<Integer> getAreaIndicesWithFilter(final Predicate<AreaTakingCard> filter) {
        final List<Integer> indices = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            if (area[i] == null) break;
            if (filter.test(area[i])) {
                indices.add(i);
            }
        }
        return indices;
    }

    public List<Choice> getAreaChoicesWithFilter(final Predicate<AreaTakingCard> filter) {
        final List<Choice> indices = new ArrayList<>(5);
        for (int i = 0; i < 5; ++i) {
            if (area[i] == null) break;
            if (filter.test(area[i])) {
                indices.add(Choice.builder().type(ChoiceType.SELF_AREA_LEADER).value(i).build());
            }
        }
        return indices;
    }

    public List<Choice> getAlliedFollowerChoices() {
        return buildChoiceList(ChoiceType.SELF_AREA_LEADER, getAlliedFollowerIndices());
    }

    public List<Choice> getBothEffectTargetFollowerChoices() {
        final List<Integer> enemyTargetFollowerIndices = new ArrayList<>(5);
        final int priority = getOpponent().collectEffectTargetFollowerIndices(enemyTargetFollowerIndices);

        final List<Choice> targetFollowerChoices = buildChoiceList(ChoiceType.ENEMY_AREA_LEADER,
                enemyTargetFollowerIndices);

        if (priority == 0) {
            targetFollowerChoices.addAll(buildChoiceList(ChoiceType.SELF_AREA_LEADER, getAlliedFollowerIndices()));
        }

        return targetFollowerChoices;
    }

    public List<List<Choice>> getPlayCardChoice(final int index) {
        assert 0 <= index && index < getHandSize();
        return this.hand.get(index).getChoices();
    }

    public List<List<Choice>> getEvolveChoice(final int index) {
        assert 0 <= index && index < 5;
        assert area[index] != null && area[index] instanceof Follower;
        return ((Follower)area[index]).getEvolveChoices();
    }

    public boolean isTurn() {
        return game.getCurrentLeader() == this;
    }

    public void retrieveOneCardFromDeck(final Predicate<CardBase> filter) {
        final List<CardBase> cardList = deck.stream().filter(filter).collect(Collectors.toList());
        if (cardList.isEmpty()) return;
        final CardBase card = cardList.get(0);
        deck.remove(card);
        shuffleDeck();
        putCardIntoHand(card);

        //TODO: resonance changed event
    }
}
