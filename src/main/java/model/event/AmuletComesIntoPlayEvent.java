package model.event;

import model.card.AreaTakingCard;

public class AmuletComesIntoPlayEvent extends AreaTakingCardComesIntoPlayEvent {
    public AmuletComesIntoPlayEvent(AreaTakingCard card) {
        super(card);
    }
}
