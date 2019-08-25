package ru.shortener.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DefaultKeyConverterService implements KeyConverterService {

    private static final char[] chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-_".toCharArray();

    private static final Map<Character, Long> charToInt =
            IntStream.rangeClosed(0, chars.length - 1)
                    .boxed()
                    .collect(Collectors.toMap(i -> (chars[i]), Long::valueOf));

    @Override
    public String idToKey(long id) {
        long n = id;
        StringBuilder builder = new StringBuilder();
        while (n != 0L) {
            builder.append(chars[(int)(n % chars.length)]);
            n /= chars.length;
        }
        return builder.reverse().toString();
    }

    @Override
    public long keyToId(String key) {
        return key.chars()
                .mapToObj(i -> (char) i)
                .map(charToInt::get)
                .reduce(0L, (a, b) -> a * chars.length + b);
    }
}
