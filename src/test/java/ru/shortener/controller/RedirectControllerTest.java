package ru.shortener.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@WebAppConfiguration
public class RedirectControllerTest {

    private static final String PATH = "/aabbccdd";
    private static final int REDIRECT_STATUS = 302;
    private static final String HEADER_NAME = "Location";
    private static final String HEADER_VALUE = "http://example.com";
    private static final String BAD_PATH = "/aaaaaaa";
    private static final int NOT_FOUND = 404;

    @Autowired
    WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void controllerMustRedirectUsWhenRequestIsSuccessful() throws Exception {
        mockMvc.perform(get(PATH))
                .andExpect(status().is(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE));
    }

    @Test
    public void controllerMustReturn404IfBadKey() throws Exception {
        mockMvc.perform(get(BAD_PATH))
                .andExpect(status().is(NOT_FOUND));
    }
}
