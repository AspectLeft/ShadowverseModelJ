package model.card.basic.swordcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.swordcraft.token.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForgeWeaponryTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(ForgeWeaponry.class, ForgeWeaponry.class).getCurrentLeader();
    }

    @Test
    void effect() {
        leader.summon(new Knight(leader));
        leader.setMaximumPpAndRecover(3);
        leader.playCardFromHand(0, leader.getAlliedFollowerChoices());

        assertEquals(3, leader.getAlliedFollowers().get(0).getAttack());
        assertEquals(3, leader.getAlliedFollowers().get(0).getDefense());
    }

    @Test
    void effect_rally() {
        leader.setRecord("Rally", 9);
        leader.summon(new Knight(leader));
        leader.setMaximumPpAndRecover(3);
        leader.playCardFromHand(0, leader.getAlliedFollowerChoices());

        assertEquals(5, leader.getAlliedFollowers().get(0).getAttack());
        assertEquals(5, leader.getAlliedFollowers().get(0).getDefense());
    }
}