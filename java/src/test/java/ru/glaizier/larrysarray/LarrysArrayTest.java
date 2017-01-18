package ru.glaizier.larrysarray;

import org.junit.Assert;
import org.junit.Test;

public class LarrysArrayTest extends Assert {
    @Test
    public void isSortable() throws Exception {
        assertTrue(LarrysArray.isSortable(new int[]{3, 1, 2}));
        assertTrue(LarrysArray.isSortable(new int[]{1, 3, 4, 2}));
        assertTrue(LarrysArray.isSortable(new int[]{1, 6, 5, 2, 4, 3}));
        assertFalse(LarrysArray.isSortable(new int[]{1, 2, 3, 5, 4}));
        assertTrue(LarrysArray.isSortable(new int[]{17, 21, 2, 1, 16, 9, 12, 11, 6, 18, 20, 7, 14, 8, 19, 10, 3}));
    }

    @Test
    public void isSortableBruteForce() throws Exception {
        assertTrue(LarrysArray.isSortableBruteForce(new int[]{3, 1, 2}));
        assertTrue(LarrysArray.isSortableBruteForce(new int[]{1, 3, 4, 2}));
        assertTrue(LarrysArray.isSortableBruteForce(new int[]{1, 6, 5, 2, 4, 3}));
        assertFalse(LarrysArray.isSortableBruteForce(new int[]{1, 2, 3, 5, 4}));
        assertTrue(LarrysArray.isSortableBruteForce(new int[]{17, 21, 2, 1, 16, 9, 12, 11, 6, 18, 20, 7, 14, 8, 19, 10, 3}));
    }

}