package ru.shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.shortener")
public class ShortenerApp {
    public static void main(String[] args) {
        SpringApplication.run(ShortenerApp.class, args);
    }
}
