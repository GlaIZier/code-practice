package ru.glaizier.jpoint2018.epam;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import ru.glaizier.jpoint2018.epam.easy.Suppliers;

public class SuppliersTest {

    @Test
    public void t() {
        Map.Entry<String, String> say = new Suppliers().say();
        assertThat(say.getKey(), is("JOKER"));
        assertThat(say.getValue(), is("JOKERAFTER"));
    }
}
