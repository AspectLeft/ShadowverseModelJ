package model.event;

import model.Leader;

public class TurnEndEvent extends EventBase implements FromLeader {

    public TurnEndEvent(final Leader leader) {
        super();
        putExtra("Leader", leader);
    }

    public Leader getLeader() {
        return (Leader) getExtra("Leader");
    }
}
