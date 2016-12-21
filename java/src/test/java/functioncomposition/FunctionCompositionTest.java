package functioncomposition;

import org.junit.Assert;
import org.junit.Test;
import ru.glaizier.functioncomposition.FunctionComposition;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionCompositionTest extends Assert {

    @Test
    public void testComposition() {
        List<Function<Integer, Integer>> functions = Arrays.asList(integer -> integer / 2,
                integer -> (int) Math.pow(integer, 2));
        assertEquals(Integer.valueOf(25), FunctionComposition.compose(functions, 10));

        List<Function<String, String>> sFunctions = Arrays.asList(String::toUpperCase, s -> s + s.length());
        assertEquals("ABC3", FunctionComposition.compose(sFunctions, "abc"));
    }


}
