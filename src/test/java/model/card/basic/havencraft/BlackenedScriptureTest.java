package model.card.basic.havencraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.ElfGuard;
import model.effect.Choice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlackenedScriptureTest {

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(BlackenedScripture.class, ElfGuard.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void effect() {
        leader.endTurn();
        opponent.setMaximumPpAndRecover(2);
        opponent.increaseCountOfCardPlayedThisTurn(2);
        opponent.playCardFromHand(0, null);
        opponent.summon(new ElfGuard(opponent));
        opponent.endTurn();

        BlackenedScripture blackenedScripture = (BlackenedScripture) leader.getHand().get(0);
        List<List<Choice>> choices = blackenedScripture.getChoices();
        assertEquals(1, choices.get(0).size());
        assertEquals(1, choices.get(0).get(0).getValue());
        leader.playCardFromHand(0, choices.get(0));
        assertEquals(0, opponent.getCountOfShadow());
        assertEquals(2, opponent.getAlliedFollowers().get(0).getAttack());
    }
}