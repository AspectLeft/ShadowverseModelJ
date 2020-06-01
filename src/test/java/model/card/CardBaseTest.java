package model.card;

import model.Game;
import model.report.NoopReporter;
import model.report.Reporter;

import java.util.Arrays;
import java.util.Collections;

public class CardBaseTest {
    public static Game setUpWithDecksOf10WithReporter(final Class<? extends CardBase> leaderCardClass,
                                                      final Class<? extends CardBase> opponentCardClass,
                                                      final Reporter reporter) {
        final Game game = new Game(1, CardClass.NEUTRAL, Collections.nCopies(10, leaderCardClass),
                2, CardClass.NEUTRAL, Collections.nCopies(10, opponentCardClass),
                () -> true, reporter);
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        return game;
    }

    public static Game setUpWithDecksOf10(final Class<? extends CardBase> leaderCardClass,
                                          final Class<? extends CardBase> opponentCardClass) {
        return setUpWithDecksOf10WithReporter(leaderCardClass, opponentCardClass, new NoopReporter());
    }
}
