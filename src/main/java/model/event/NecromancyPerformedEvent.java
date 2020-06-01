package model.event;

import model.Entity;

public class NecromancyPerformedEvent extends EventBase {
    public NecromancyPerformedEvent(final Entity source, final int value) {
        super();
        putExtra("Source", source);
        putExtra("Value", value);
    }
}
