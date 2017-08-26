package ru.shortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shortener.repository.LinkRepository;

@Component
public class DefaultKeyMapperService implements KeyMapperService {

    @Autowired
    private LinkRepository linkRepository;

    @Override
    public void add(String key, String url) {
    }

    @Override
    public String getLink(String key) {
        return null;
    }
}
