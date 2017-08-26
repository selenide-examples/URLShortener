package ru.shortener.repositories;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import ru.shortener.ShortenerApp;

@TestPropertySource(locations = "classpath:application-test.properties")
@TestExecutionListeners(DbUnitTestExecutionListener.class)
@SpringBootTest(classes = ShortenerApp.class)
@DirtiesContext
abstract class AbstractRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
}
