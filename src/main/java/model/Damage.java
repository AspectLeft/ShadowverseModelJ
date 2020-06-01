package model;

import lombok.Data;

@Data
public class Damage {
    private int value;
    private DamageType type;

    public Damage(int value, DamageType type) {
        this.value = value;
        this.type = type;
    }
    public void reduce(int delta) {
        this.value -= delta;
        if (this.value < 0) this.value = 0;
    }
}
