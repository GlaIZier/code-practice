package ru.glaizier.prevnextprimenumber;

import org.junit.Assert;
import org.junit.Test;
import ru.glaizier.PrevNextPrimeNumber;
import ru.glaizier.util.Pair;

public class PrevNextPrimeNumberTest extends Assert {

    @Test
    public void test() {
        assertEquals(new Pair<>((long) 2, (long) 5), PrevNextPrimeNumber.get(3));
        assertEquals(new Pair<Long, Long>(null, (long) 3), PrevNextPrimeNumber.get(2));
        assertEquals(new Pair<>((long) 999999937, (long) 1000000009), PrevNextPrimeNumber.get(1000000007));
    }

}
