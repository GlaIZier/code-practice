package ru.glaizier.com.hackerrank.swaptwonumbers;

import org.junit.Assert;
import org.junit.Test;
import ru.glaizier.util.Pair;

public class TwoNumbersSwapperTest extends Assert {

    @Test
    public void testSwap() {
        assertEquals(TwoNumbersSwapper.swap(3, 4), new Pair<>(4, 3));
        assertEquals(TwoNumbersSwapper.swap(-3, 4), new Pair<>(4, -3));
        assertEquals(TwoNumbersSwapper.swap(-1000, -10), new Pair<>(-10, -1000));
        assertEquals(TwoNumbersSwapper.swap(0, 0), new Pair<>(0, 0));
    }

    @Test
    public void testBitOperationsSwap() {
        assertEquals(TwoNumbersSwapper.bitOperationsSwap(3, 4), new Pair<>(4, 3));
        assertEquals(TwoNumbersSwapper.bitOperationsSwap(-3, 4), new Pair<>(4, -3));
        assertEquals(TwoNumbersSwapper.bitOperationsSwap(-1000, -10), new Pair<>(-10, -1000));
        assertEquals(TwoNumbersSwapper.bitOperationsSwap(0, 0), new Pair<>(0, 0));
    }

}
