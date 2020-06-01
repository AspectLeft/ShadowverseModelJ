package model.event;

import model.Leader;

public class MaximumPpChangeEvent extends EventBase implements FromLeader {
    public MaximumPpChangeEvent(final Leader leader, final int oldValue, final int newValue) {
        super();
        putExtra("Leader", leader);
        putExtra("OldValue", oldValue);
        putExtra("NewValue", newValue);
    }

    public Leader getLeader() {
        return (Leader) getExtra("Leader");
    }
}
