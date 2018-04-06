package ru.glaizier.jpoint2016;

import org.junit.Test;

public class CovarianceTest {

    @Test
    public void covarianceTest() {
        //        List<Object> ll = new ArrayList<Integer>(); // won't compile

        Integer[] ii = {1, 2, 3};
        Number[] nn = ii;
//        nn[0] = new Float(23.4); // java.lang.ArrayStoreException: java.lang.Float
        System.out.println((Integer) nn[0]);
//        System.out.println((Float) nn[1]); // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.Float

    }

}
