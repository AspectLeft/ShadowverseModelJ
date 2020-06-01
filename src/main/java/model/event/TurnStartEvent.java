package model.event;

import model.Leader;

public class TurnStartEvent extends EventBase implements FromLeader {
    public TurnStartEvent(final Leader leader) {
        super();
        putExtra("Leader", leader);
    }

    public Leader getLeader() {
        return (Leader) getExtra("Leader");
    }
}
