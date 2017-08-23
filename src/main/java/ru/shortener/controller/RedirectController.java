package ru.shortener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/{key}")
public class RedirectController {
    private final String HEADER_NAME = "Location";

    @RequestMapping()
    public void redirect(@PathVariable("key") String key, HttpServletResponse response) {
        if (key.equals("aabbccdd")) {
            response.setHeader(HEADER_NAME, "http://example.com");
            response.setStatus(302);
        } else
            response.setStatus(404);
    }
}
