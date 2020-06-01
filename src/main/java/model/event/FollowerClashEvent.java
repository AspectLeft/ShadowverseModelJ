package model.event;

import model.Entity;
import model.card.CardBase;

public class FollowerClashEvent extends EventBase implements FromCard {
    public FollowerClashEvent(final CardBase card, final Entity target) {
        super();
        putExtra("Card", card);
        putExtra("Entity", target);
    }

    @Override
    public CardBase getCard() {
        return (CardBase) getExtra("Card");
    }

    public Entity getTarget() {
        return (Entity) getExtra("Entity");
    }
}
