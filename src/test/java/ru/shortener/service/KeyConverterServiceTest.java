package ru.shortener.service;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class KeyConverterServiceTest {


    private KeyConverterService keyConverterService = new DefaultKeyConverterService();

    @Test
    public void givenIdMustBeConvertableBothWays() {
        Random random = new Random();
        for (long i = 0; i < 1000L; i++) {
            long initialId = Math.abs(random.nextLong());
            String key = keyConverterService.idToKey(initialId);
            long id = keyConverterService.keyToId(key);
            Assert.assertEquals(initialId, id);
        }
    }
}
