package ru.glaizier.emassupercomputer;

import org.junit.Assert;
import org.junit.Test;

public class EmasSupercomputerTest extends Assert {

    @Test
    public void getCrossesProduct() {
        assertEquals(5, EmasSupercomputer.getCrossesProduct(new boolean[][]{
                {true, true, true, true, true, true},
                {true, false, false, false, true, false},
                {true, true, true, true, true, true},
                {true, true, false, false, true, false},
                {true, true, true, true, true, true}
        }));
        assertEquals(25, EmasSupercomputer.getCrossesProduct(new boolean[][]{
                {false, true, false, false, true, false},
                {true, true, true, true, true, true},
                {false, true, false, false, true, false},
                {true, true, true, true, true, true},
                {false, true, false, false, true, false},
                {false, true, false, false, true, false}
        }));
    }

}