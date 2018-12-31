package ru.shortener.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shortener.model.Link;

import java.util.List;

@Repository
public interface LinkRepository extends CrudRepository<Link, Long> {
    List<Link> findAll();
}
