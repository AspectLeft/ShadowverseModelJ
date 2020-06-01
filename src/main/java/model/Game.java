package model;

import lombok.Getter;
import lombok.NonNull;
import model.card.AreaTakingCard;
import model.card.CardBase;
import model.card.CardClass;
import model.card.Follower;
import model.card.amulet.Amulet;
import model.effect.Choice;
import model.effect.EffectBase;
import model.effect.TriggerAbleEffect;
import model.event.EvalDamageEvent;
import model.event.EventBase;
import model.exception.DataInconsistentException;
import model.exception.GameEndingException;
import model.report.Reporter;
import model.report.SimpleReporter;
import util.CoinFlipper;
import util.RandomUtil;

import java.util.*;

public class Game {
    @Getter private final Random random = new Random(System.currentTimeMillis());
    @Getter private final Reporter reporter;

    private final Leader firstLeader;
    private final Leader secondLeader;
    private Leader winner = null;

    private GameState state;
    private int finishedChangeCardsOnStartCount = 0;
    private boolean isTurnOfFirstLeader;
    //Just for Dimension Shift
    private boolean togglingTurn = true;

    private transient Queue<EffectBase> effectQueue = new LinkedList<>();

    public Game(final int id1, final CardClass leader1Class, final Collection<Class<? extends CardBase>> deck1,
                final int id2, final CardClass leader2Class, final Collection<Class<? extends CardBase>> deck2) {
        this(id1, leader1Class, deck1, id2, leader2Class, deck2, RandomUtil::flipCoin, new SimpleReporter());
    }

    public Game(final int id1, final CardClass leader1Class, final Collection<Class<? extends CardBase>> deck1,
                final int id2, final CardClass leader2Class, final Collection<Class<? extends CardBase>> deck2,
                final CoinFlipper coinFlipper, final Reporter reporter) {
        assert id1 != id2;
        this.reporter = reporter;
        state = GameState.START;
        if (coinFlipper.flipCoin()) {
            this.firstLeader = new Leader(this, id1, leader1Class, true, deck1);
            this.secondLeader = new Leader(this, id2, leader2Class, false, deck2);
        }
        else {
            this.firstLeader = new Leader(this, id2, leader2Class, true, deck2);
            this.secondLeader = new Leader(this, id1, leader1Class, false, deck1);
        }
    }

    public void startGame() {
        firstLeader.drawCardsOnStart(3);
        secondLeader.drawCardsOnStart(3);
    }

    public Leader getCurrentLeader() {
        return isTurnOfFirstLeader ? firstLeader : secondLeader;
    }

    public Leader getLeaderById(final int id) throws DataInconsistentException {
        if (firstLeader.getId() == id) {
            return firstLeader;
        }
        if (secondLeader.getId() == id) {
            return secondLeader;
        }
        throw new DataInconsistentException("Strange ID");
    }

    public void changeCardsOnStart(final int id, final List<Boolean> changeFlags)
            throws GameEndingException, DataInconsistentException {
        getLeaderById(id).changeCardsOnStart(changeFlags);
    }

    void finishedChangeCardsOnStart() throws GameEndingException {
        assert state == GameState.START;
        finishedChangeCardsOnStartCount++;
        if (finishedChangeCardsOnStartCount == 2) {
            state = GameState.TURN;
            this.isTurnOfFirstLeader = true;
            getCurrentLeader().startTurn();
        }
    }

    public void endTurn() throws GameEndingException {
        if (togglingTurn) {
            this.isTurnOfFirstLeader = !this.isTurnOfFirstLeader;
        }
        else {
            // Dimension Shift
            togglingTurn = true;
        }

        getCurrentLeader().startTurn();
    }

    public void endGame() {
        state = GameState.ENDING;
    }

    public void triggerAndSettle(@NonNull final EventBase event) throws GameEndingException {
        triggerEffect(event);
        settleAllEffects();
    }

    public void triggerEffect(@NonNull final EventBase event) throws GameEndingException {
        for (final List<EffectBase> effects: collectEffects()) {
            for (final EffectBase effect: effects) {
                if (! (effect instanceof TriggerAbleEffect)) continue;
                if (((TriggerAbleEffect) effect).canTrigger(event)) {
                    effectQueue.add(effect);
                }
            }
        }
    }

    // Eval damage can cut in
    public void evalDamage(@NonNull final EvalDamageEvent evalDamageEvent) {
        final Queue<EffectBase> evalDamageQueue = new LinkedList<>();
        for (final List<EffectBase> effects: collectEffects()) {
            for (final EffectBase effect: effects) {
                if (! (effect instanceof TriggerAbleEffect)) continue;
                if (((TriggerAbleEffect) effect).canTrigger(evalDamageEvent)) {
                    evalDamageQueue.add(effect);
                }
            }
        }
        while (!evalDamageQueue.isEmpty()) {
            evalDamageQueue.poll().settle();
        }
    }

    private List<List<EffectBase>> collectEffects() {
        final Leader currentLeader = getCurrentLeader();
        final Leader counterpartLeader = currentLeader == firstLeader ? secondLeader : firstLeader;
        final List<List<EffectBase>> allEffects = new ArrayList<>();

        allEffects.add(currentLeader.getEffects());
        for (final AreaTakingCard card: currentLeader.getArea()) {
            collectCardEffects(allEffects, card);
        }
        allEffects.add(counterpartLeader.getEffects());
        for (final CardBase card: counterpartLeader.getArea()) {
            collectCardEffects(allEffects, card);
        }
        return allEffects;
    }

    public void endGame(final Leader winner) {
        assert winner == firstLeader || winner == secondLeader;
        this.winner = winner;
        this.state = GameState.ENDING;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    private void collectFollowerEffects(final List<List<EffectBase>> allEffects, Follower follower) {
        allEffects.add(follower.isEvolved() ? follower.getEvolvedEffects() : follower.getUnevolvedEffects());
        allEffects.add(follower.getAttachedEffects());
    }

    private void collectAmuletEffects(final List<List<EffectBase>> allEffects, Amulet amulet) {
        allEffects.add(amulet.collectEffects());
    }

    private void collectCardEffects(final List<List<EffectBase>> allEffects, CardBase card) {
        if (card instanceof Follower) {
            collectFollowerEffects(allEffects, (Follower) card);
        }
        else if (card instanceof Amulet) {
            collectAmuletEffects(allEffects, (Amulet) card);
        }
    }

    public void useCard(final int leaderId, final int handIndex, final List<Choice> options)
            throws DataInconsistentException, GameEndingException {
        getLeaderByIdAndCheckTurn(leaderId).playCardFromHand(handIndex, options);
    }

    public void evolve(final int leaderId, final int areaIndex, final List<Choice> options)
            throws DataInconsistentException, GameEndingException {
        getLeaderByIdAndCheckTurn(leaderId).evolve(areaIndex, options);
    }

    private Leader getLeaderByIdAndCheckTurn(final int leaderId) throws DataInconsistentException {
        final Leader leader = getLeaderById(leaderId);
        if (leader != getCurrentLeader()) {
            throw new DataInconsistentException("Not your turn");
        }
        return leader;
    }

    public void pushEffect(final EffectBase effectBase) {
        this.effectQueue.add(effectBase);
    }

    public void settleAllEffects() {
        while (!effectQueue.isEmpty() && this.state != GameState.ENDING) {
            effectQueue.poll().settle();
        }
    }

    Leader getCounterpart(final Leader leader) throws DataInconsistentException {
        if (leader == firstLeader) {
            return secondLeader;
        }
        if (leader == secondLeader) {
            return firstLeader;
        }
        throw new DataInconsistentException("Internal error: strange leader");
    }
}
