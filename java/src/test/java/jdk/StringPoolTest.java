package jdk;

import org.junit.Assert;
import org.junit.Test;


public class StringPoolTest extends Assert {

    @Test
    public void testEquals() {
        String firstName = "John";
        String lastName = "Smith";

        String name1 = "JohnSmith";
        String name2 = "John" + "Smith";
        String name3 = firstName + lastName;

        System.out.println("name1 == name2 : " + (name1 == name2));
        System.out.println("name1 == name2 : " + (name1 == name3));
        System.out.println("name1 == name2 : " + (name2 == name3));
        System.out.println("name1 == name2 : " + (name2.intern() == name3.intern()));

        String s1 = "Cat";
        String s2 = "Cat";
        String s3 = new String("Cat");
        String s4 = new String("Cat");

        System.out.println("s1 == s2 :" + (s1 == s2));
        System.out.println("s1 == s3 :" + (s1 == s3));
        System.out.println("s3 == s4 :" + (s3 == s4));
        System.out.println("s3.intern() == s4.intern() :" + (s3.intern() == s4.intern()));
    }


}
