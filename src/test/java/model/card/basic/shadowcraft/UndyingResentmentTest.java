package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UndyingResentmentTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(UndyingResentment.class, WaterFairy.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void effect() {
        leader.endTurn();
        WaterFairy waterFairy = (WaterFairy) opponent.playCardFromHand(0, null);
        opponent.endTurn();
        leader.playCardFromHand(0, leader.getEnemyEffectTargetFollowerChoices());

        assertEquals(-2, waterFairy.getDefense());
    }

    @Test
    void necromancy() {
        leader.endTurn();
        WaterFairy waterFairy = (WaterFairy) opponent.playCardFromHand(0, null);
        opponent.endTurn();
        leader.increaseShadow(2);
        leader.playCardFromHand(0, leader.getEnemyEffectTargetFollowerChoices());

        assertEquals(-4, waterFairy.getDefense());
        assertEquals(1, leader.getCountOfShadow());
    }
}