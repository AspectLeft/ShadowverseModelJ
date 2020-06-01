package model;

import model.card.CardBase;
import model.card.CardClass;

import java.util.*;

// TODO: incomplete
public class Leader {
    public static final int DEFAULT_MAXIMUM_DEFENSE = 20;
    public static final int MAXIMUM_EP_FIRST = 2;
    public static final int MAXIMUM_EP_SECOND = 3;

    private final int id;

    private int maximumDefense = DEFAULT_MAXIMUM_DEFENSE;
    private int defense = DEFAULT_MAXIMUM_DEFENSE;

    private int maximumPp = 0;
    private int pp = 0;

    private final boolean isFirst;

    private final int maximumEp;
    private int ep;

    private final CardClass leaderClass;

    private LinkedList<CardBase> deck = new LinkedList<>();
    private Iterator<CardBase> deckIterator = deck.iterator();

    private LinkedList<CardBase> hand = new LinkedList<>();

    Leader(final int id, final CardClass leaderClass, final boolean isFirst, final Collection<CardBase> deck) {
        this.id = id;
        assert leaderClass != CardClass.NEUTRAL;
        this.leaderClass = leaderClass;
        this.isFirst = isFirst;
        this.ep = this.maximumEp = isFirst ? MAXIMUM_EP_FIRST : MAXIMUM_EP_SECOND;
        this.deck.addAll(deck);
        Collections.shuffle(this.deck);
    }

    public void startTurn() {

    }

    public void drawCard() {

    }

    public void playCard(CardBase card) {

    }

    public void endTurn() {

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



    public Collection<CardBase> getHand() {
        return this.hand;
    }


    public int getMaximumDefense() {
        return maximumDefense;
    }

    public int getDefense() {
        return defense;
    }

    public int getMaximumPp() {
        return maximumPp;
    }

    public int getPp() {
        return pp;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public int getMaximumEp() {
        return maximumEp;
    }

    public int getEp() {
        return ep;
    }

    public CardClass getLeaderClass() {
        return leaderClass;
    }
}
