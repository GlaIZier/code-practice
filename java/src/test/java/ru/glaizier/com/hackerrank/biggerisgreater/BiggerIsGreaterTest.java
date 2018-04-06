package ru.glaizier.com.hackerrank.biggerisgreater;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

public class BiggerIsGreaterTest extends Assert {
    @Test
    public void getSmallestGreater() throws Exception {
        assertEquals("ba", BiggerIsGreater.getSmallestGreater("ab"));
        assertEquals("bb", BiggerIsGreater.getSmallestGreater("bb"));
        assertEquals("hegf", BiggerIsGreater.getSmallestGreater("hefg"));
        assertEquals("hcdk", BiggerIsGreater.getSmallestGreater("dkhc"));
        assertEquals("", BiggerIsGreater.getSmallestGreater(""));
        Assertions.assertThatThrownBy(() -> BiggerIsGreater.getSmallestGreater(null)).isInstanceOf(AssertionError.class);
    }

}