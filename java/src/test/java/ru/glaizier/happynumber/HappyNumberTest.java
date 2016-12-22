package ru.glaizier.happynumber;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HappyNumberTest extends Assert {

    @Test
    public void isHappyNumberTest() {
        assertTrue(HappyNumber.isHappyNumber(7));
        assertTrue(HappyNumber.isHappyNumber(1));
        assertFalse(HappyNumber.isHappyNumber(0));
        assertFalse(HappyNumber.isHappyNumber(2));
        assertFalse(HappyNumber.isHappyNumber(Integer.MAX_VALUE));
        assertThatThrownBy(() -> HappyNumber.isHappyNumber(-1)).isInstanceOf(IllegalArgumentException.class);
    }
}
