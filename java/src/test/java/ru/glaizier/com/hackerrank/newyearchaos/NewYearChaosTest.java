package ru.glaizier.com.hackerrank.newyearchaos;

import org.junit.Assert;
import org.junit.Test;

public class NewYearChaosTest extends Assert {
    @Test
    public void getSwaps() throws Exception {
        assertEquals(3, NewYearChaos.getSwaps(new int[]{2, 1, 5, 3, 4}));
        assertEquals(-1, NewYearChaos.getSwaps(new int[]{2, 5, 1, 3, 4}));
        assertEquals(1, NewYearChaos.getSwaps(new int[]{1, 2, 3, 5, 4}));
    }

}