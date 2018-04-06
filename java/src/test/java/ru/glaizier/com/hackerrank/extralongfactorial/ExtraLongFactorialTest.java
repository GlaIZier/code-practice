package ru.glaizier.com.hackerrank.extralongfactorial;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class ExtraLongFactorialTest extends Assert {
    @Test
    public void calculate() throws Exception {
        Assertions.assertThatThrownBy(() -> ExtraLongFactorial.calculate(0)).isInstanceOf(AssertionError.class);
        assertEquals(new BigInteger("15511210043330985984000000"), ExtraLongFactorial.calculate(25));
        assertEquals(new BigInteger("1"), ExtraLongFactorial.calculate(1));

        Assertions.assertThatThrownBy(() -> ExtraLongFactorial.calculateRecursive(0)).isInstanceOf(AssertionError.class);
        assertEquals(new BigInteger("15511210043330985984000000"), ExtraLongFactorial.calculateRecursive(25));
        assertEquals(new BigInteger("1"), ExtraLongFactorial.calculateRecursive(1));

        Assertions.assertThatThrownBy(() -> ExtraLongFactorial.calculateTailRecursive(0)).isInstanceOf(AssertionError.class);
        assertEquals(new BigInteger("15511210043330985984000000"), ExtraLongFactorial.calculateTailRecursive(25));
        assertEquals(new BigInteger("1"), ExtraLongFactorial.calculateTailRecursive(1));
    }

}