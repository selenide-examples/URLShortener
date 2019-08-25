package ru.shortener.repositories;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shortener.model.Link;
import ru.shortener.repository.LinkRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

@DatabaseSetup("/datasets/link-table.xml")
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = LinkRepositoryTest.DATASET)
public class LinkRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private LinkRepository repository;

    final static String DATASET = "/datasets/link-table.xml";

    private final static Long LINK_NOT_FOUND  = 1L;

    private final static Long LINK_1_ID = 100500L;

    private final static String LINK_TBS_TEXT = "http://www.ru";

    private final static String LINK_1_TEXT = "http://www.ya.ru";

    @Test
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
    public void findOneNotExisting() {
        Optional<Link> got = repository.findById(LINK_NOT_FOUND);
        assertThat(got.isPresent(), equalTo(false));
    }

    @Test
    public void saveNewLink() {
        Link linkToBeSave = new Link();
        linkToBeSave.setUrl(LINK_TBS_TEXT);
        Link linkSaved = repository.save(linkToBeSave);
        List<Link> linkList = repository.findAll();
        assertThat(linkList, hasSize(4));
        assertThat(linkSaved.getUrl(), equalTo(LINK_TBS_TEXT));
    }
}
