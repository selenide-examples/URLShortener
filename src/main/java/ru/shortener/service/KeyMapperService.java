package ru.shortener.service;

public interface KeyMapperService {
     String add(String link);
     String getLink(String key);
}
