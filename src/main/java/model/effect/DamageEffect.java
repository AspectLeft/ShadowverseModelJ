package model.effect;

import model.Entity;
import model.Leader;
import model.card.Follower;
import model.exception.GameEndingException;
import model.report.Report;

public class DamageEffect extends EffectWithOptions {
    public DamageEffect(Entity owner) {
        super(owner);
    }

    @Override
    public void settle() throws GameEndingException {
        if (owner instanceof Follower) {
            if (!((Follower) owner).isInArea()) {
                return;
            }
        }
        final Leader leader = owner.getLeader();
        leader.report(Report.builder().type(Report.ReportType.TAKE_DAMAGE)
                .id(leader.getId()).name(owner.getName()).build());
        owner.takeDamage(options.get(0).getValue());
    }
}
