package model.card;

import lombok.Getter;
import model.Damage;
import model.DamageType;
import model.Entity;
import model.Leader;
import model.effect.*;
import model.event.*;
import model.exception.DataInconsistentException;
import model.exception.GameEndingException;
import model.report.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class Follower extends AreaTakingCard {
    protected final int defaultUnevolvedAttack;
    protected final int defaultUnevolvedDefense;
    protected final int defaultEvolvedAttack;
    protected final int defaultEvolvedDefense;

    @Getter protected final List<EffectBase> unevolvedEffects = new ArrayList<>();
    @Getter protected final List<EffectBase> evolvedEffects = new ArrayList<>();
    @Getter protected final List<EffectBase> attachedEffects = new ArrayList<>();

    protected boolean isEvolved = false;

    protected int attack;
    protected int defense;
    protected int maximumDefense;

    protected boolean firstTurnInArea = false;

    protected int maximumAttackCount = 1;
    protected int attackCount = 0;

    private Follower() {
        super(null, Rarity.BRONZE, CardClass.NEUTRAL,0);
        this.defaultUnevolvedAttack = 0;
        this.defaultUnevolvedDefense = 0;
        this.defaultEvolvedAttack = 0;
        this.defaultEvolvedDefense = 0;
    }

    protected Follower(final Leader leader, final Rarity rarity, final CardClass defaultCardClass, final int defaultCost,
                       final int defaultUnevolvedAttack, final int defaultUnevolvedDefense,
                       final int defaultEvolvedAttack, final int defaultEvolvedDefense) {
        super(leader, rarity, defaultCardClass, defaultCost);
        this.attack = this.defaultUnevolvedAttack = defaultUnevolvedAttack;
        this.defense = this.maximumDefense = this.defaultUnevolvedDefense = defaultUnevolvedDefense;
        this.defaultEvolvedAttack = defaultEvolvedAttack;
        this.defaultEvolvedDefense = defaultEvolvedDefense;
        initEffects();
    }

    @Override
    public void reset() {
        this.attack = this.defaultUnevolvedAttack;
        this.defense = this.maximumDefense = this.defaultUnevolvedDefense;
        this.unevolvedEffects.clear();
        this.evolvedEffects.clear();
        this.attachedEffects.clear();
        this.isEvolved = false;
        super.reset();
    }

    public void evolve(final List<Choice> options) throws DataInconsistentException {
        assert leader != null;
        if (isEvolved()) {
            throw new DataInconsistentException("Already evolved");
        }
        this.isEvolved = true;
        this.attack = this.defaultEvolvedAttack;
        this.defense += this.defaultEvolvedDefense - this.defaultUnevolvedDefense;
        this.maximumDefense = this.defaultEvolvedDefense;
        refreshBuff();
        for (final EffectBase effect: this.collectEffects()) {
            if (effect instanceof Evolve) {
                ((Evolve) effect).setOptions(options);
                this.getGame().pushEffect(effect);
            }
        }
        //TODO: send evolve event
    }

    public List<List<Choice>> getEvolveChoices() {
        for (final EffectBase effect: this.evolvedEffects) {
            if (effect instanceof Evolve) {
                return ((Evolve) effect).getChoices();
            }
        }
        return null;
    }

    public void refreshBuff() {
        this.attack = this.isEvolved ? this.defaultEvolvedAttack : this.defaultUnevolvedAttack;

        final int damageTaken = this.maximumDefense - this.defense;
        this.maximumDefense = this.isEvolved ? this.defaultEvolvedDefense : this.defaultUnevolvedDefense;
        this.defense = this.maximumDefense - damageTaken;
        restoreBuffs();
        if (this.attack < 0) {
            this.attack = 0;
        }
        if (this.defense <= 0) {
            this.destroy();
        }
    }

    public boolean isEvolved() {
        return isEvolved;
    }

    @Override
    public void comesIntoPlay() throws GameEndingException {
        this.firstTurnInArea = true;
        leader.addCardToArea(this);
        leader.incrementRecord("Rally");
        leader.report(Report.builder().type(Report.ReportType.CARD_INTO_PLAY).id(leader.getId())
                .name(getName()).build());
        leader.getGame().triggerEffect(new FollowerComesIntoPlayEvent(this));
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void attachAndSettleIncrementalBuff(final int attackBuff, final int defenseBuff) {
        final IncrementalBuff buff = new IncrementalBuff(this, attackBuff, defenseBuff);
        this.attachEffect(buff);
        buff.settle();
    }

    private void applyBuff(final Buff buff) {
        final int attackBuff = buff.getAttackBuff();
        final int defenseBuff = buff.getDefenseBuff();
        if (buff instanceof IncrementalBuff) {
            this.attack += attackBuff;
            this.defense += defenseBuff;
            this.maximumDefense += defenseBuff;
            return;
        }
        if (buff instanceof FixedBuff) {
            this.attack = attackBuff;
            this.maximumDefense = this.defense = defenseBuff;
        }
    }

    private void restoreBuffs() {
        for (final EffectBase effect: this.attachedEffects) {
            if (effect instanceof Buff) {
                applyBuff((Buff) effect);
            }
        }
    }

    public void getBuffed(final Buff buff) {
        restoreBuffs();
        leader.report(Report.builder().type(Report.ReportType.BUFFED).id(leader.getId()).name(getName())
                .values(new int[]{buff.getAttackBuff(), buff.getDefenseBuff()}).build());

        if (this.attack < 0) {
            this.attack = 0;
        }
        if (this.defense <= 0) {
            this.destroy();
        }
        // TODO: trigger buffed effects
    }

    public boolean freeToEvolve() {
        for (EffectBase effectBase: collectEffects()) {
            if (effectBase instanceof FreeToEvolve) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<EffectBase> collectEffects() {
        List<EffectBase> effects = new ArrayList<>();
        effects.addAll(isEvolved ? this.evolvedEffects : this.unevolvedEffects);
        effects.addAll(attachedEffects);
        return effects;
    }

    public int getMaximumDefense() {
        return maximumDefense;
    }

    public boolean canAttackFollower() {
        if (attackCount == maximumAttackCount) return false;
        //TODO: locked
        if (!firstTurnInArea) {
            return true;
        }
        for (final EffectBase effect: collectEffects()) {
            if (effect instanceof Storm || effect instanceof Rush) {
                return true;
            }
        }
        return isEvolved;
    }

    public boolean canAttackLeader() {
        if (attackCount == maximumAttackCount) return false;
        // TODO: locked
        // TODO: Bahamut, Maisha
        if (!firstTurnInArea) {
            return true;
        }
        for (final EffectBase effect: collectEffects()) {
            if (effect instanceof Storm) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void startTurn() {
        super.startTurn();
        assert isInArea();
        firstTurnInArea = false;
        attackCount = 0;
        // TODO: eval attached effects
    }

    public void takeDamage(int value) throws GameEndingException {
        this.defense -= value;
        leader.report(Report.builder().type(Report.ReportType.TAKE_DAMAGE).id(leader.getId())
                .name(getName()).values(new int[]{value}).build());
        if (this.defense <= 0) {
            this.destroy();
        }
    }

    @Override
    protected void destroyed() throws GameEndingException {
        super.destroyed();
        this.getGame().triggerEffect(new FollowerDestroyedEvent(this));
    }

    private void removeEffectIf(Predicate<? super EffectBase> filter) {
        this.unevolvedEffects.removeIf(filter);
        this.evolvedEffects.removeIf(filter);
        this.attachedEffects.removeIf(filter);
    }

    private void strikeDamage(final int damageValue, final Entity target) {
        giveDamage(DamageType.ATTACK, damageValue, target);
    }

    public void strike(final Entity target) throws GameEndingException, DataInconsistentException {
        this.attackCount++;
        removeEffectIf(effectBase -> effectBase instanceof Ambush);
        assert leader != null;
        this.leader.getGame().triggerAndSettle(new FollowerStrikeEvent(this, target));
        if (!this.isInArea()) return;
        if (target instanceof Follower) {
            final Follower opponent = (Follower) target;
            this.leader.getGame().triggerAndSettle(new FollowerClashEvent(this, target));
            if (!this.isInArea()) return;
            if (!opponent.isInArea()) return;
            final Damage giveDamage = new Damage(this.getAttack(), DamageType.ATTACK);
            final Damage takeDamage = new Damage(opponent.getAttack(), DamageType.ATTACK);
            this.leader.getGame().evalDamage(new EvalDamageEvent(this, target, giveDamage));
            this.leader.getGame().evalDamage(new EvalDamageEvent(target, this, takeDamage));

            this.defense -= takeDamage.getValue();
            opponent.defense -= giveDamage.getValue();
            if (this.defense <= 0) {
                this.destroy();
            }
            if (opponent.defense <= 0) {
                opponent.destroy();
            }
            if (this.hasEffect(effect -> effect instanceof Drain)) {
                leader.heal(giveDamage.getValue());
            }
            if (this.isInArea() && opponent.hasBane()) {
                this.tryDestroy();
            }
            if (opponent.isInArea() && this.hasBane()){
                opponent.tryDestroy();
            }
            return;
        }
        if (target instanceof Leader) {
            final Leader opponent = (Leader) target;
            // this.leader.getGame().triggerEffect(new FollowerClashEvent(this, target));
            if (!this.isInArea()) return;
            final Damage giveDamage = new Damage(this.getAttack(), DamageType.ATTACK);
            this.leader.getGame().triggerEffect(new EvalDamageEvent(this, target, giveDamage));
            opponent.takeDamage(giveDamage.getValue());
            if (this.hasEffect(effect -> effect instanceof Drain)) {
                leader.heal(giveDamage.getValue());
            }
            return;
        }
        throw new DataInconsistentException("Weird target");
    }

    public void attachEffect(final EffectBase effect) {
        this.attachedEffects.add(effect);
    }

    public boolean detachEffect(final EffectBase effect) {
        return this.attachedEffects.remove(effect);
    }

    public int getTargetPriority() {
        int targetPriority = 0;
        final List<EffectBase> effects = collectEffects();
        for (final EffectBase effect: effects) {
            if (effect instanceof NotAttackable || effect instanceof Ambush) {
                return -1;
            }
            if (effect instanceof Ward) {
                targetPriority = 1;
            }
        }
        return targetPriority;
    }

    public boolean hasBane() {
        return hasEffect(effect -> effect instanceof Bane);
    }

    public boolean hasWard() {
        return hasEffect(effectBase -> effectBase instanceof Ward);
    }

    public boolean detachBuff(final Buff buff) {
        if (attachedEffects.remove(buff)) {
            refreshBuff();
            return true;
        }
        return false;
    }

    public void heal(final int healValue) {
        this.defense = Math.min(this.maximumDefense, this.defense + healValue);
        leader.report(Report.builder().type(Report.ReportType.HEAL).id(leader.getId())
                .name(getName()).values(new int[]{healValue}).build());
    }
}
