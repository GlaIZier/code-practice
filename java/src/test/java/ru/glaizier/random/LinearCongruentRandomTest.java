package ru.glaizier.random;

import org.junit.Assert;
import org.junit.Test;

public class LinearCongruentRandomTest extends Assert {

    @Test
    public void testRandom() {
        RandomInterface random = new LinearCongruentRandom(100);
        /*
        $seq = new Random(100); $result1 = $seq->getNext(); $result2 = $seq->getNext(); $this->assertNotEquals($result1, $result2); $seq->reset(); $result21 = $seq->getNext(); $result22 = $seq->getNext(); $this->assertEquals($result1, $result21); $this->assertEquals($result2, $result22);
         */
        int result1 = random.getNext();
        int result2 = random.getNext();
        assertNotEquals(result1, result2);

        random.reset();
        int result21 = random.getNext();
        int result22 = random.getNext();
        assertEquals(result1, result21);
        assertEquals(result2, result22);
    }

}
