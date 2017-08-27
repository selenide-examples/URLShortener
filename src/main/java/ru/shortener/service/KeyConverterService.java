package ru.shortener.service;

public interface KeyConverterService {
    String idToKey(long id);
    long keyToId(String key);
}
