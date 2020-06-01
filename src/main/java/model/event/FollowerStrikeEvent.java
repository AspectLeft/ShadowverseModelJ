package model.event;

import model.Entity;
import model.card.CardBase;
import model.card.Follower;

public class FollowerStrikeEvent extends EventBase implements FromCard {
    public FollowerStrikeEvent(final Follower card, final Entity target) {
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
