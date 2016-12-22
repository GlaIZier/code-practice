package ru.glaizier.util;

import org.junit.Assert;
import org.junit.Test;

public class PairTest extends Assert {

    @Test
    public void testEquals() {
        assertEquals(new Pair<>(1, 10), new Pair<>(1, 10));
        assertEquals(new Pair<Integer, Integer>(1, null), new Pair<Integer, Integer>(1, null));
        assertEquals(new Pair<Integer, Integer>(null, 10), new Pair<Integer, Integer>(null, 10));
        assertEquals(new Pair<Integer, Integer>(null, null), new Pair<Integer, Integer>(null, null));
        Pair<Integer, Integer> pair = new Pair<>(1, 1);
        assertEquals(pair, pair);

        assertNotEquals(new Pair<>(10, 1), new Pair<>(1, 10));
        assertNotEquals(new Pair<Integer, Integer>(null, null), null);
        assertNotEquals(new Pair<Integer, Integer>(null, null), new Object());
    }

}
