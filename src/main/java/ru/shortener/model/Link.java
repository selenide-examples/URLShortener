package ru.shortener.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "links")
@Data
public class Link {

    @Id
    @GeneratedValue
    private Long id;

    private String url = "";
}
