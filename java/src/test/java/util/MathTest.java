package util;

import org.junit.Assert;
import org.junit.Test;
import ru.glaizier.util.Math;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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


    @Test
    public void testIsPowerOfTwo() {
        assertTrue(Math.isPowerOfTwo(1));
        assertTrue(Math.isPowerOfTwo(2));
        assertFalse(Math.isPowerOfTwo(3));
        assertFalse(Math.isPowerOfTwo(Integer.MAX_VALUE));
        assertThatThrownBy(() -> Math.isPowerOfTwoLogarithmic(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Math.isPowerOfTwoLogarithmic(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @SuppressWarnings("NumericOverflow")
    public void testIsPowerOfTwoLogarithmic() {
        assertTrue(Math.isPowerOfTwoLogarithmic(1));
        assertTrue(Math.isPowerOfTwoLogarithmic(2));
        assertTrue(Math.isPowerOfTwoLogarithmic(4));
        assertTrue(Math.isPowerOfTwoLogarithmic(32));
        assertFalse(Math.isPowerOfTwoLogarithmic(Integer.MAX_VALUE));

        assertThatThrownBy(() -> Math.isPowerOfTwoLogarithmic(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Math.isPowerOfTwoLogarithmic(Integer.MAX_VALUE + 1)).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    public void testIsPowerOfThree() {
    }

}
