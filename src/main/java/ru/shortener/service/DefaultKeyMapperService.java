package ru.shortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shortener.model.Link;
import ru.shortener.repository.LinkRepository;

import java.util.Optional;

@Component
public class DefaultKeyMapperService implements KeyMapperService {

    @Autowired
    private KeyConverterService converterService;

    @Autowired
    private LinkRepository repository;

    @Override
    public String add(String url) {
        Link link = new Link();
        link.setUrl(url);
        return converterService.idToKey(repository.save(link).getId());
    }

    @Override
    public Link getLink(String key) {
        Long id = converterService.keyToId(key);
        Optional<Link> res = repository.findOne(id);
        if (!res.isPresent()) {
            throw new RuntimeException("Link with key non exists: " + key);
        }
        return res.get();
    }
}
