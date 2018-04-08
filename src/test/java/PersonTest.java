package bookmarks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = hello.Application.class)
@WebAppConfiguration
public class PersonTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private hello.PersonRepository personRepository;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void BasicTest() {
        assertEquals(1, 1);
    }

    @Test
    public void emptyPeople() throws Exception {
        mockMvc.perform(get("/people")
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void CreatePerson() throws Exception {
        mockMvc.perform(post("/people")
                .content("{\n\t\"firstName\": \"Victor\",\n\t\"lastName\": \"Raul\"\n}")
                .contentType(contentType))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/people")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.people[0].firstName", is("Victor")))
                .andExpect(jsonPath("$._embedded.people[0].lastName", is("Raul")));
    }
}