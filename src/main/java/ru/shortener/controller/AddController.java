package ru.shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shortener.model.AddRequest;
import ru.shortener.model.AddResponse;
import ru.shortener.service.KeyMapperService;

@Controller
@RequestMapping("/add")
public class AddController {
    @Value("${url.shortener.prefix}")
    private String prefix;

    @Autowired
    private KeyMapperService service;

    @RequestMapping(value = "/rest", method = RequestMethod.POST)
    @ResponseBody
    public AddResponse addRest(@RequestBody AddRequest request) {
        AddResponse response = new AddResponse();
        response.setKey(service.add(request.getLink()));
        response.setLink(request.getLink());
        return response;
    }

    @RequestMapping(value = "/html", method = RequestMethod.POST)
    public String addHtml(Model model, @RequestParam(value = "link", required = true) String link ) {
        AddResponse response = add(link);
        model.addAttribute("link", response.getLink());
        model.addAttribute("keyed", prefix + response.getKey());
        return "result";
    }

    private AddResponse add(String link) {
        AddResponse response = new AddResponse();
        response.setKey(service.add(link));
        response.setLink(link);
        return response;
    }
}
