package ru.glaizier.com.hackerrank.emassupercomputer;

import org.junit.Assert;
import org.junit.Test;

public class EmasSuperComputerTest extends Assert {

    @Test
    public void getCrossesProduct() {
        assertEquals(5, EmasSuperComputer.getCrossesSquareProduct(new boolean[][]{
                {true, true, true, true, true, true},
                {true, false, false, false, true, false},
                {true, true, true, true, true, true},
                {true, true, false, false, true, false},
                {true, true, true, true, true, true}
        }));
        assertEquals(25, EmasSuperComputer.getCrossesSquareProduct(new boolean[][]{
                {false, true, false, false, true, false},
                {true, true, true, true, true, true},
                {false, true, false, false, true, false},
                {true, true, true, true, true, true},
                {false, true, false, false, true, false},
                {false, true, false, false, true, false}
        }));
    }

}