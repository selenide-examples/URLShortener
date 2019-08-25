package ru.shortener.repositories;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import ru.shortener.Application;

@TestExecutionListeners(DbUnitTestExecutionListener.class)
@SpringBootTest(classes = Application.class)
@DirtiesContext
abstract class AbstractRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
}
