package ru.glaizier.jpoint2016;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveListElementTest extends Assert {

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(2, 3, 4, 5, 6));
        removeByIndex(list, 3);
        assertEquals(Arrays.asList(2, 4, 5, 6), list);
    }

    private static void removeByIndex(List<Integer> list, Integer index) {
        list.remove(index);
    }

}
