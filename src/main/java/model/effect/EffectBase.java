package model.effect;

import model.Entity;
import model.Leader;
import model.exception.GameEndingException;
import model.report.Report;

public abstract class EffectBase {
    protected Entity owner;

    private EffectBase() {}

    public EffectBase(final Entity owner) {
        this.owner = owner;
    }

    public void settle() throws GameEndingException {}

    public Entity getOwner() {
        return owner;
    }

    public void report(final int[] values) {
        final Leader leader = owner.getLeader();
        leader.report(Report.builder().type(Report.ReportType.ACTIVATED_EFFECT)
                .id(leader.getId()).name(owner.getName()).values(values).build());
    }
}
