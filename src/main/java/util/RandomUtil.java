package util;

import java.util.List;
import java.util.Random;

public class RandomUtil {
    public static boolean flipCoin() {
        return Math.random() >= 0.5d;
    }

    public static <E> E pickOne(final Random random, final List<E> list) {
        if (list == null || list.isEmpty()) return null;
        return list.get(random.nextInt(list.size()));
    }
}
