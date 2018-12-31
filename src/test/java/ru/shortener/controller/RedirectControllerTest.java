package ru.shortener.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.shortener.ShortenerApp;
import ru.shortener.model.Link;
import ru.shortener.service.DefaultKeyMapperService;
import ru.shortener.service.KeyMapperService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShortenerApp.class)
@WebAppConfiguration
@ContextConfiguration(classes = RedirectControllerTest.TestConfig.class)
public class RedirectControllerTest {

    private static final String PATH = "aabbccdd";
    private static final int REDIRECT_STATUS = 302;
    private static final String HEADER_NAME = "Location";
    private static final String HEADER_VALUE = "http://example.com";
    private static final String BAD_PATH = "aaaaaaa";
    private static final int NOT_FOUND = 404;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void controllerMustRedirectUsWhenRequestIsSuccessful() throws Exception {
        mockMvc.perform(get("/"+PATH))
                .andExpect(status().is(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE));
    }

    @Test
    public void controllerMustReturn404IfBadKey() throws Exception {
        mockMvc.perform(get(BAD_PATH))
                .andExpect(status().is(NOT_FOUND));
    }

    @Test
    public void homePageWorkPerfect() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(MockMvcResultMatchers.view().name("home"));
    }

    @TestConfiguration
    public static class TestConfig {

        @Bean
        @Primary
        public KeyMapperService keyMapperService() {
            KeyMapperService keyMapperService = Mockito.mock(DefaultKeyMapperService.class);
            Link link = new Link();
            link.setUrl(HEADER_VALUE);
            Mockito.when(keyMapperService.getLink(PATH)).thenReturn(link);
            return keyMapperService;
        }
    }
}
