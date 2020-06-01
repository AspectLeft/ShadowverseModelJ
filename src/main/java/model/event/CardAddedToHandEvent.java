package model.event;

import model.card.CardBase;

public class CardAddedToHandEvent extends EventBase implements FromCard {
    public CardAddedToHandEvent(final CardBase card) {
        super();
        putExtra("Card", card);
    }

    @Override
    public CardBase getCard() {
        return (CardBase) getExtra("Card");
    }
}
