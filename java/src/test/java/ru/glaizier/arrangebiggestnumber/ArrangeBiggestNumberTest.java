package ru.glaizier.arrangebiggestnumber;

import org.junit.Assert;
import org.junit.Test;

public class ArrangeBiggestNumberTest extends Assert {

    @Test
    public void testArrange() {
        assertEquals("4324", ArrangeBiggestNumber.arrange(new String[]{"3", "24", "4"}));
        assertEquals("9987", ArrangeBiggestNumber.arrange(new String[]{"7", "98", "9"}));
        assertEquals("1410", ArrangeBiggestNumber.arrange(new String[]{"1", "14", "0"}));
        assertEquals("1", ArrangeBiggestNumber.arrange(new String[]{"1"}));
        assertEquals("998764543431", ArrangeBiggestNumber.arrange(new String[]{"1", "34", "3", "98", "9", "76", "45", "4"}));
    }

}