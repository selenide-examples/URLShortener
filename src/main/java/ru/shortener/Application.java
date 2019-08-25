package ru.shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.shortener.properties.ShortenerConfigProperties;

@EnableConfigurationProperties(ShortenerConfigProperties.class)
@SpringBootApplication(scanBasePackages = "ru.shortener")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
