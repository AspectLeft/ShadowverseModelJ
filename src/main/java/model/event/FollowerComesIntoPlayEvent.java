package model.event;

import model.card.Follower;

public class FollowerComesIntoPlayEvent extends AreaTakingCardComesIntoPlayEvent {
    public FollowerComesIntoPlayEvent(final Follower card) {
        super(card);
    }
}
