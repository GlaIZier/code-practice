package util;

import org.junit.Assert;
import org.junit.Test;
import ru.glaizier.util.Tuple;

public class TupleTest extends Assert {

    @Test
    public void testEquals() {
        assertEquals(new Tuple<Integer, Integer>(1, 10), new Tuple<Integer, Integer>(1, 10));
        assertEquals(new Tuple<Integer, Integer>(1, null), new Tuple<Integer, Integer>(1, null));
        assertEquals(new Tuple<Integer, Integer>(null, 10), new Tuple<Integer, Integer>(null, 10));
        assertEquals(new Tuple<Integer, Integer>(null, null), new Tuple<Integer, Integer>(null, null));
        Tuple<Integer, Integer> tuple = new Tuple<Integer, Integer>(1, 1);
        assertEquals(tuple, tuple);

        assertNotEquals(new Tuple<Integer, Integer>(10, 1), new Tuple<Integer, Integer>(1, 10));
        assertNotEquals(new Tuple<Integer, Integer>(null, null), null);
        assertNotEquals(new Tuple<Integer, Integer>(null, null), new Object());
    }


}
