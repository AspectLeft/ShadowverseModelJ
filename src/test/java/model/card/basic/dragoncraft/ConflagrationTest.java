package model.card.basic.dragoncraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.Follower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.CardCopyUtil;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConflagrationTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(Conflagration.class, Conflagration.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void effect() {
        opponent.summon(CardCopyUtil.createNCardInstances(5, opponent, DreadDragon.class).stream()
                .map(card -> (Follower) card).collect(Collectors.toList()));
        leader.summon(CardCopyUtil.createNCardInstances(5, leader, DreadDragon.class).stream()
                .map(card -> (Follower) card).collect(Collectors.toList()));
        leader.setMaximumPpAndRecover(7);
        leader.playCardFromHand(0, null);

        assertTrue(leader.getAlliedFollowers().isEmpty());
        assertTrue(opponent.getAlliedFollowers().isEmpty());
    }

}