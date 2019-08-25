package ru.shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shortener.model.AddRequest;
import ru.shortener.model.AddResponse;
import ru.shortener.properties.ShortenerConfigProperties;
import ru.shortener.service.KeyMapperService;

@Controller
@RequestMapping("/add")
public class AddController {

    private final KeyMapperService service;

    private final ShortenerConfigProperties config;

    @Autowired
    public AddController(KeyMapperService service, ShortenerConfigProperties config) {
        this.service = service;
        this.config = config;
    }

    @PostMapping(value = "/rest")
    @ResponseBody
    public AddResponse addRest(@RequestBody AddRequest request) {
        AddResponse response = new AddResponse();
        response.setKey(service.add(request.getLink()));
        response.setLink(request.getLink());
        return response;
    }

    @PostMapping(value = "/html")
    public String addHtml(Model model, @RequestParam(value = "link") String link) {
        AddResponse response = add(link);
        model.addAttribute("link", response.getLink());
        model.addAttribute("keyed", config.getPrefix() + response.getKey());
        return "result";
    }

    private AddResponse add(String link) {
        AddResponse response = new AddResponse();
        response.setKey(service.add(link));
        response.setLink(link);
        return response;
    }
}
