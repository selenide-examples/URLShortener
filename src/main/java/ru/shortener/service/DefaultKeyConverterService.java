package ru.shortener.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DefaultKeyConverterService implements KeyConverterService {

    private char[] chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-_".toCharArray();
    private Map<Character, Long> charToInt =
            IntStream.rangeClosed(0, chars.length - 1)
                    .mapToObj(i -> Integer.valueOf(i))
                    .collect(Collectors.toMap(i -> (chars[i]), i -> Long.valueOf(i)));


    @Override
    public String idToKey(long id) {
        Long n = id;
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
