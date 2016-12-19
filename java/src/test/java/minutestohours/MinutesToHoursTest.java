package minutestohours;

import org.junit.Assert;
import org.junit.Test;
import ru.glaizier.minutestohours.MinutesToHours;
import ru.glaizier.util.Pair;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MinutesToHoursTest extends Assert {

    @Test
    public void testConvert() {
        assertEquals(new Pair<>(0, 31), MinutesToHours.convert(31));
        assertEquals(new Pair<>(2, 59), MinutesToHours.convert(179));
        assertEquals(new Pair<>(0, 0), MinutesToHours.convert(0));
        assertEquals(new Pair<>(1, 0), MinutesToHours.convert(60));
        assertThatThrownBy(() -> MinutesToHours.convert(-1)).isInstanceOf(IllegalArgumentException.class);
    }

}
