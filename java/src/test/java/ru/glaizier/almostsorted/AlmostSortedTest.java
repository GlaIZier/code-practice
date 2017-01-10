package ru.glaizier.almostsorted;

import org.junit.Assert;
import org.junit.Test;

public class AlmostSortedTest extends Assert {
    @Test
    public void canBeSorted() throws Exception {
        assertEquals("no", AlmostSorted.canBeSorted(new int[]{3, 1, 2}));
        assertEquals("swap 0-1", AlmostSorted.canBeSorted(new int[]{4, 2}));
        assertEquals("swap 3-6", AlmostSorted.canBeSorted(new int[]{1, 2, 3, 10, 5, 6, 4, 11}));
        assertEquals("reverse 1-4", AlmostSorted.canBeSorted(new int[]{1, 5, 4, 3, 2, 6}));
    }

}