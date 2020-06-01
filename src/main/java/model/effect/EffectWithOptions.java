package model.effect;

import model.Entity;

import java.util.List;

public abstract class EffectWithOptions extends EffectBase {
    protected List<Choice> options;

    public EffectWithOptions(Entity owner) {
        super(owner);
    }

    public void setOptions(List<Choice> options) {
        this.options = options;
    }

    public List<List<Choice>> getChoices() {
        return null;
    }
}
