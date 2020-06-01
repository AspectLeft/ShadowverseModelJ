package model.event;

import model.card.CardBase;
import model.card.Follower;

public class FollowerDestroyedEvent extends EventBase implements FromCard {
    public FollowerDestroyedEvent(final Follower follower) {
        super();
        putExtra("Card", follower);
    }

    @Override
    public CardBase getCard() {
        return (CardBase) getExtra("Card");
    }
}
