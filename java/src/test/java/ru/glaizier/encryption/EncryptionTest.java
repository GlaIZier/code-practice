package ru.glaizier.encryption;

import org.junit.Assert;
import org.junit.Test;

public class EncryptionTest extends Assert {

    @Test
    public void encrypt() throws Exception {
        assertEquals("hae and via ecy", Encryption.encrypt("haveaniceday"));
        assertEquals("fto ehg ee dd", Encryption.encrypt("feedthedog"));
        assertEquals("", Encryption.encrypt(""));
    }

}