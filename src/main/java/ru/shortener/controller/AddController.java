package ru.shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.shortener.model.AddRequest;
import ru.shortener.model.AddResponse;
import ru.shortener.service.KeyMapperService;

@Controller
@RequestMapping("/add")
public class AddController {
    @Value("url.shortener.prefix")
    private String prefix;

    @Autowired
    private KeyMapperService service;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AddResponse add(@RequestBody AddRequest request) {
        AddResponse response = new AddResponse();
        response.setKey(service.add(request.getLink()));
        response.setLink(request.getLink());
        return response;
    }
}
