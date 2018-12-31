package ru.shortener.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ConfigurationProperties(prefix = "shortener")
public class ShortenerConfigProperties {

    @NotBlank
    private String prefix;
}
