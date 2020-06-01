package model.event;

import model.card.CardBase;
import model.card.amulet.Amulet;

public class AmuletDestroyedEvent extends EventBase implements FromCard {
    public AmuletDestroyedEvent(final Amulet amulet) {
        super();
        putExtra("Card", amulet);
    }

    @Override
    public CardBase getCard() {
        return (CardBase) getExtra("Card");
    }
}
