package model.event;

import model.card.CardBase;

public class CardDrewEvent extends EventBase implements FromCard {
    public CardDrewEvent(final CardBase card) {
        super();
        putExtra("Card", card);
    }

    @Override
    public CardBase getCard() {
        return (CardBase) getExtra("Card");
    }
}
