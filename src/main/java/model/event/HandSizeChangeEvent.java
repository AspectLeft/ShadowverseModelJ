package model.event;

import model.Leader;

public class HandSizeChangeEvent extends EventBase implements FromLeader {
    public HandSizeChangeEvent(final Leader leader, final int before, final int after) {
        super();
        putExtra("Leader", leader);
        putExtra("Before", before);
        putExtra("After", after);
    }

    @Override
    public Leader getLeader() {
        return (Leader) getExtra("Leader");
    }
}
