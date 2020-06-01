package model.card.spell;

public interface Spellboost {
    void increaseSpellboost(final int delta);

    int getSpellboost();
}
