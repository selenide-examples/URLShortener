package ru.shortener.service;

interface KeyMapperService {
     String add(String link);
     String getLink(String key);
}
