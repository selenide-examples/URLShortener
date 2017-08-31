package ru.shortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.shortener.model.Link;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DefaultKeyMapperService implements KeyMapperService {

    private Map<Long, Link> map = new HashMap<>();

    @Autowired
    KeyConverterService converterService;

    private AtomicLong sequence = new AtomicLong(10000000L);

    @Override
    public String add(String url) {
        Long id = sequence.getAndIncrement();
        Link link = new Link();
        link.setUrl(url);
        map.put(id, link);
        return converterService.idToKey(id);
    }

    @Override
    public Link getLink(String key) {
        Long id = converterService.keyToId(key);
        Assert.notNull(map.get(id), "Link with key non exists: " + key);
        return map.get(id);
    }
}
