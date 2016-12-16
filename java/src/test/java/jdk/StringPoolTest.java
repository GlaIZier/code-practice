package jdk;

import org.junit.Assert;
import org.junit.Test;


public class StringPoolTest extends Assert {

    @SuppressWarnings({"ConstantConditions", "StringEquality"})
    @Test
    public void testEquals() {
        String firstName = "John";
        String lastName = "Smith";

        String name1 = "JohnSmith";
        String name2 = "John" + "Smith";
        String name3 = firstName + lastName;

        assertTrue(name1 == name2);
        assertFalse(name1 == name3);
        assertFalse(name2 == name3);
        assertTrue(name2.intern() == name3.intern());

        String s1 = "Cat";
        String s2 = "Cat";
        @SuppressWarnings("RedundantStringConstructorCall") String s3 = new String("Cat");
        @SuppressWarnings("RedundantStringConstructorCall") String s4 = new String("Cat");

        assertTrue(s1 == s2);
        assertFalse(s1 == s3);
        assertFalse(s3 == s4);
        assertTrue(s3.intern() == s4.intern());
    }


}
