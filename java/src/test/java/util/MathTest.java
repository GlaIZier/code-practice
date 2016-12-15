package util;

import org.junit.Assert;
import org.junit.Test;
import ru.glaizier.util.Math;

public class MathTest extends Assert {

    @Test
    public void testIsPrime() {
        assertTrue(Math.isPrime(3));
        assertTrue(Math.isPrime(5));
        assertTrue(Math.isPrime(1000000007));
        assertFalse(Math.isPrime(-1000));
        assertFalse(Math.isPrime(0));
        assertFalse(Math.isPrime(1));
        assertFalse(Math.isPrime(16));
        assertFalse(Math.isPrime(6));
    }

}
