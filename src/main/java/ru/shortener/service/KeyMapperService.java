package ru.shortener.service;

import ru.shortener.model.Link;

public interface KeyMapperService {

    String add(String link);

    Link getLink(String key);
}
