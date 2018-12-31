package ru.shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shortener.model.Link;
import ru.shortener.service.KeyMapperService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping
public class RedirectController {
    private static final String HEADER_NAME = "Location";

    private final KeyMapperService service;

    @Autowired
    public RedirectController(KeyMapperService service) {
        this.service = service;
    }

    @RequestMapping("/{key}")
    public void redirect(@PathVariable("key") String key, HttpServletResponse response) {
        Link link = service.getLink(key);
        if (link != null) {
            response.setHeader(HEADER_NAME, link.getUrl());
            response.setStatus(302);
        } else
            response.setStatus(404);
    }

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
