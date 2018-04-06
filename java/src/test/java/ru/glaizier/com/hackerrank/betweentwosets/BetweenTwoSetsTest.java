package ru.glaizier.com.hackerrank.betweentwosets;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class BetweenTwoSetsTest extends Assert {

    @Test
    public void testGetBetweenSet() {
        assertEquals(new HashSet<>(Arrays.asList(4, 8, 16)),
                BetweenTwoSets.getBetweenSet(new HashSet<>(Arrays.asList(2, 4)), new HashSet<>(Arrays.asList(16, 32, 96))));
        assertEquals(new HashSet<>(),
                BetweenTwoSets.getBetweenSet(new HashSet<>(Arrays.asList(2, 4)), new HashSet<>(Arrays.asList(2, 32, 96))));
        Assertions.assertThatThrownBy(() -> BetweenTwoSets.getBetweenSet(null, null)).isInstanceOf(AssertionError.class);
    }


}
