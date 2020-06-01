package model.event;

import model.card.AreaTakingCard;
import model.card.CardBase;

public class AreaTakingCardComesIntoPlayEvent extends EventBase implements FromCard {
    public AreaTakingCardComesIntoPlayEvent(final AreaTakingCard card) {
        super();
        putExtra("Card", card);
    }

    @Override
    public CardBase getCard() {
        return (CardBase) getExtra("Card");
    }
}
