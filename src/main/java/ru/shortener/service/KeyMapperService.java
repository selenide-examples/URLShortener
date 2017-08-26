package ru.shortener.service;

interface KeyMapperService {
     void add(String key, String link);
     String getLink(String key);
}
