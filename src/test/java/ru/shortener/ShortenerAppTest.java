package ru.shortener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication(scanBasePackages = "ru.shortener")
@WebAppConfiguration
@ContextConfiguration
public class ShortenerAppTest {

    @Test
    public void contextLoads() {

    }
}
