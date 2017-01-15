package ru.glaizier.larrysarray;

import org.junit.Assert;
import org.junit.Test;

public class LarrysArrayTest extends Assert {
    @Test
    public void isSortable() throws Exception {
        assertTrue(LarrysArray.isSortable(new int[]{3, 1, 2}));
        assertTrue(LarrysArray.isSortable(new int[]{1, 3, 4, 2}));
        assertFalse(LarrysArray.isSortable(new int[]{1, 2, 3, 5, 4}));
    }

}