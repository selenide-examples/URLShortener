package ru.shortener.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddResponse {
    private String link;
    private String key;
}
