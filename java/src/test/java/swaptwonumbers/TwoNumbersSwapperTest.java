package swaptwonumbers;

import org.junit.Assert;
import org.junit.Test;
import ru.glaizier.swaptwonumbers.TwoNumbersSwapper;
import ru.glaizier.util.Tuple;

public class TwoNumbersSwapperTest extends Assert {

    @Test
    public void testSwap() {
        assertEquals(TwoNumbersSwapper.swap(3, 4), new Tuple<Integer, Integer>(4, 3));
        assertEquals(TwoNumbersSwapper.swap(-3, 4), new Tuple<Integer, Integer>(4, -3));
        assertEquals(TwoNumbersSwapper.swap(-1000, -10), new Tuple<Integer, Integer>(-10, -1000));
        assertEquals(TwoNumbersSwapper.swap(0, 0), new Tuple<Integer, Integer>(0, 0));
    }

    @Test
    public void testBitOperationsSwap() {
        assertEquals(TwoNumbersSwapper.bitOperationsSwap(3, 4), new Tuple<Integer, Integer>(4, 3));
        assertEquals(TwoNumbersSwapper.bitOperationsSwap(-3, 4), new Tuple<Integer, Integer>(4, -3));
        assertEquals(TwoNumbersSwapper.bitOperationsSwap(-1000, -10), new Tuple<Integer, Integer>(-10, -1000));
        assertEquals(TwoNumbersSwapper.bitOperationsSwap(0, 0), new Tuple<Integer, Integer>(0, 0));
    }

}
