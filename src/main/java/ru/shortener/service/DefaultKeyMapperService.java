package ru.shortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DefaultKeyMapperService implements KeyMapperService {

    private Map<Long, String> map = new HashMap<>();

    @Autowired
    KeyConverterService converterService;

    private AtomicLong sequence = new AtomicLong(10000000L);

    @Override
    public String add(String url) {
        Long id = sequence.getAndIncrement();
        map.put(id, url);
        return converterService.idToKey(id);
    }

    @Override
    public String getLink(String key) {
        Long id = converterService.keyToId(key);
        if(map.get(id) == null) {
            throw  new RuntimeException();
        } else return map.get(id);
    }
}
