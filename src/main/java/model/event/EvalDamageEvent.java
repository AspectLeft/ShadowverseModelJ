package model.event;

import model.Damage;
import model.Entity;

public class EvalDamageEvent extends EventBase {
    public EvalDamageEvent(Entity source, Entity target, Damage damage) {
        super();
        putExtra("Source", source);
        putExtra("Target", target);
        putExtra("Damage", damage);
    }

    public Entity getSource() {
        return (Entity) getExtra("Source");
    }

    public Entity getTarget() {
        return (Entity) getExtra("Target");
    }

    public Damage getDamage() {
        return (Damage) getExtra("Damage");
    }
}
