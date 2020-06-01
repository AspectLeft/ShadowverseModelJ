package util;

import model.Leader;
import model.card.CardBase;
import model.exception.DataInconsistentException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardCopyUtil {
    public static CardBase copy(final Leader leader, final CardBase card) throws DataInconsistentException  {
        try {
            return card.getClass().getDeclaredConstructor(Leader.class).newInstance(leader);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new DataInconsistentException("Strange card type");
        }
    }

    public static CardBase createCardInstance(final Leader leader, final Class<? extends CardBase> cardType)
            throws DataInconsistentException {
        try {
            return cardType.getDeclaredConstructor(Leader.class).newInstance(leader);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new DataInconsistentException("Strange card type ");
        }
    }

    public static List<CardBase> createNCardInstances(final int n, final Leader leader,
                                                      final Class<? extends CardBase> cardType)
            throws DataInconsistentException {
        return Stream.generate(() -> createCardInstance(leader, cardType)).limit(n).collect(Collectors.toList());
    }
}
