package ru.shortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shortener.model.Link;
import ru.shortener.repository.LinkRepository;

import java.util.Optional;

@Component
public class DefaultKeyMapperService implements KeyMapperService {

    private final KeyConverterService converterService;

    private final LinkRepository repository;

    @Autowired
    public DefaultKeyMapperService(KeyConverterService converterService, LinkRepository repository) {
        this.converterService = converterService;
        this.repository = repository;
    }

    @Override
    public String add(String url) {
        Link link = new Link();
        link.setUrl(url);
        return converterService.idToKey(repository.save(link).getId());
    }

    @Override
    public Link getLink(String key) {
        Long id = converterService.keyToId(key);
        Optional<Link> res = repository.findById(id);
        if (!res.isPresent()) {
            throw new RuntimeException("Link with key non exists: " + key);
        }
        return res.get();
    }
}
