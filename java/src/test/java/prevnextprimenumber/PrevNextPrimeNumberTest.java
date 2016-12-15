package prevnextprimenumber;

import org.junit.Assert;
import org.junit.Test;
import ru.glaizier.prevnextprimenumber.PrevNextPrimeNumber;
import ru.glaizier.util.Tuple;

public class PrevNextPrimeNumberTest extends Assert {

    @Test
    public void test() {
        assertEquals(new Tuple<Long, Long>((long) 2, (long) 5), PrevNextPrimeNumber.get(3));
        assertEquals(new Tuple<Long, Long>(null, (long) 3), PrevNextPrimeNumber.get(2));
        assertEquals(new Tuple<Long, Long>((long) 999999937, (long) 1000000009), PrevNextPrimeNumber.get(1000000007));
    }

}
