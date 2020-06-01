package model.card.amulet;

import lombok.Getter;
import model.Leader;
import model.card.CardClass;
import model.card.Rarity;
import model.exception.GameEndingException;

public abstract class CountdownAmulet extends Amulet {
    protected final int defaultCountdown;

    @Getter
    protected int countDownValue;

    public CountdownAmulet(final Leader leader, final Rarity rarity, final CardClass defaultCardClass,
                           final int defaultCost, final int countdown) {
        super(leader, rarity, defaultCardClass, defaultCost);
        this.defaultCountdown = this.countDownValue = countdown;
    }

    @Override
    public void reset() {
        this.countDownValue = this.defaultCountdown;
        super.reset();
    }

    @Override
    public void startTurn() {
        super.startTurn();
        decrement(false);
    }

    public void decrement(final boolean byEffect) throws GameEndingException {
        decreaseCount(1, byEffect);
    }

    public void decreaseCount(final int delta, final boolean byEffect) throws GameEndingException{
        assert delta > 0;
        this.countDownValue -= delta;
        if (this.countDownValue < 0) {
            this.countDownValue = 0;
        }
        // TODO: by effect
        if (this.countDownValue == 0) {
            this.destroy();
        }
    }
}
