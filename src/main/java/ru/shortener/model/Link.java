package ru.shortener.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "links")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "links_sequence")
    @SequenceGenerator(name = "links_sequence", sequenceName = "links_seq")
    private long id = 0;
    private String url = "";
}
