package ru.shortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.shortener.Application;
import ru.shortener.model.AddRequest;
import ru.shortener.service.KeyMapperService;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc
public class AddControllerTest {

    private static final String LINK = "key";

    private static final String KEY = "link";

    @MockBean
    private KeyMapperService keyMapperService;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        when(keyMapperService.add(LINK)).thenReturn(KEY);
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
}
