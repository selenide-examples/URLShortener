package ru.shortener.repositories;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.shortener.model.Link;
import ru.shortener.repository.LinkRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@DBRider
@DataJpaTest
public class LinkRepositoryTest {

    @Autowired
    private LinkRepository repository;

    private final static Long LINK_NOT_FOUND = 1L;

    private final static Long LINK_1_ID = 100500L;

    private final static String LINK_TBS_TEXT = "http://www.ru";

    private final static String LINK_1_TEXT = "http://www.ya.ru";

    @Test
    @DataSet("link.yml")
    public void findOneExisting() {
        Optional<Link> got = repository.findById(LINK_1_ID);
        assertThat(got.isPresent(), equalTo(true));
        Link linkFromDb = got.get();
        Link link = new Link();
        link.setId(LINK_1_ID);
        link.setUrl(LINK_1_TEXT);
        assertThat(linkFromDb, equalTo(link));
    }

    @Test
    @DataSet("link.yml")
    public void findOneNotExisting() {
        Optional<Link> got = repository.findById(LINK_NOT_FOUND);
        assertThat(got.isPresent(), equalTo(false));
    }

    @Test
    @DataSet("link.yml")
    public void saveNewLink() {
        Link linkToBeSave = new Link();
        linkToBeSave.setUrl(LINK_TBS_TEXT);
        Link linkSaved = repository.save(linkToBeSave);
        List<Link> linkList = repository.findAll();
        assertThat(linkList, hasSize(4));
        assertThat(linkSaved.getUrl(), equalTo(LINK_TBS_TEXT));
    }
}
