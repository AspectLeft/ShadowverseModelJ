package model.event;

import model.Leader;

public class LeaderHealedEvent extends EventBase implements FromLeader {
    public LeaderHealedEvent(final Leader leader) {
        super();
        putExtra("Leader", leader);
    }

    @Override
    public Leader getLeader() {
        return (Leader) getExtra("Leader");
    }
}
