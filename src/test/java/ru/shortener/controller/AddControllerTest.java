package ru.shortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.shortener.ShortenerApp;
import ru.shortener.model.AddRequest;
import ru.shortener.service.DefaultKeyMapperService;
import ru.shortener.service.KeyMapperService;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(locations = "classpath:application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShortenerApp.class})
@WebAppConfiguration
@ContextConfiguration(classes = AddControllerTest.TestConfig.class)
public class AddControllerTest {
    private static final String LINK = "key";
    private static final String KEY = "link";

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
    public void whenUserAddLinkHeTakesAKey() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AddRequest request = new AddRequest();
        request.setLink(LINK);
        String jsonInString = mapper.writeValueAsString(request);
        mockMvc.perform(post("/add/rest")
                .content(jsonInString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.key", equalTo(KEY)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.link", equalTo(LINK)));

    }

    @Test
    public void whenUserAddLinkByFormHeTakesAWebPage() throws Exception {
        mockMvc.perform(post("/add/html")
                .param("link", LINK)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(KEY)))
                .andExpect(MockMvcResultMatchers.content().string(containsString("http://localhost:8080/link")));
    }

    @TestConfiguration
    public static class TestConfig {

        @Bean
        @Primary
        public KeyMapperService keyMapperService() {
            KeyMapperService keyMapperService = Mockito.mock(DefaultKeyMapperService.class);
            when(keyMapperService.add(LINK)).thenReturn(KEY);
            return keyMapperService;
        }
    }
}
