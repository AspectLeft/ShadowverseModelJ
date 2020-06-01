package model.event;

import model.card.CardBase;

public class PlayCardEvent extends EventBase implements FromCard {
    public PlayCardEvent(final CardBase card) {
        super();
        putExtra("Card", card);
    }

    @Override
    public CardBase getCard() {
        return (CardBase) getExtra("Card");
    }
}
