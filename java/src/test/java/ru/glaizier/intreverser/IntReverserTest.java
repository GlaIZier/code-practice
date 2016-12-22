package ru.glaizier.intreverser;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IntReverserTest extends Assert {

    @Test
    public void testReverse() {
        assertEquals(0, IntReverser.reverse(0));
        assertEquals(-321, IntReverser.reverse(-123));
        assertEquals(123, IntReverser.reverse(321));
        assertEquals(1, IntReverser.reverse(100));
        assertEquals(-101, IntReverser.reverse(-1010));
        assertThatThrownBy(() -> IntReverser.reverse(Integer.MAX_VALUE)).isInstanceOf(IllegalArgumentException.class);
    }


}
