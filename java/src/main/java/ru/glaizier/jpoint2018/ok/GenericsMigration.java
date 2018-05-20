package ru.glaizier.jpoint2018.ok;

import com.sun.corba.se.impl.encoding.EncapsInputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class GenericsMigration {

    // what can be assigned?
    public static void main(String[] args) {
        List<?> l = Arrays.asList("1", 2, 3.0);
        List<Comparable> l2 = Arrays.asList("1", 2, 3.0);
        List<Serializable> l3 = Arrays.asList("1", 2, 3.0);
        List<? super Comparable> l4 = Arrays.asList("1", 2, 3.0);
        List<? super Serializable> l5 = Arrays.asList("1", 2, 3.0);
        List<? extends Serializable> l6 = Arrays.asList("1", 2, 3.0);
        List<? extends Comparable> l7 = Arrays.asList("1", 2, 3.0);
        List<? super Serializable> l8 = Arrays.asList("1", 2, 3.0);
//        List<? extends Comparable & Serializable> l9 = Arrays.asList("1", 2, 3.0); // can't use & with ? only with ur created class
//        List<? super Comparable & Serializable> l10 = Arrays.asList("1", 2, 3.0); // can't use & with wildcards and even ur type can't be restricted by super as it doesn't make sense and equals to just Object
    }

}
