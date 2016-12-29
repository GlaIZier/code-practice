package ru.glaizier.usaeuropetimeconverter;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UsaToEuropeTimeTest extends Assert {

    @Test
    public void testConvert() {
        assertThatThrownBy(() -> UsaToEuropeTime.convert("60:78:10AM")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> UsaToEuropeTime.convert("00:01:10FF")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> UsaToEuropeTime.convert("-02:01:10AM")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> UsaToEuropeTime.convert("")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> UsaToEuropeTime.convert(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> UsaToEuropeTime.convert("19:00:10AM")).isInstanceOf(IllegalArgumentException.class);

        assertEquals("19:05:45", UsaToEuropeTime.convert("07:05:45PM"));
        assertEquals("07:05:45", UsaToEuropeTime.convert("07:05:45AM"));
        assertEquals("00:00:00", UsaToEuropeTime.convert("12:00:00AM"));
        assertEquals("12:00:00", UsaToEuropeTime.convert("12:00:00PM"));
    }

}
