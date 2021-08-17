package opgg.weba.JamPick.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import opgg.weba.JamPick.domain.Genre;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import opgg.weba.JamPick.domain.Genre;
import com.fasterxml.jackson.databind.ObjectMapper;
import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.dto.RouletteRecCriteriaDto;
import opgg.weba.JamPick.repository.RouletteRecRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class RouletteRecControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RouletteRecRepository rouletteRecRepository;

    @Autowired EntityManager em;

    @BeforeEach
    public void before() {

        IndieApp indieApp1 = new IndieApp();
        indieApp1.setId(11L);
        indieApp1.setName("Hi");
        indieApp1.setHeaderImage("url1");
        em.persist(indieApp1);

        Genre genre1 = new Genre();
        genre1.setGenreId(11L);
        genre1.setDescription("호러");
        genre1.setIndieApp(indieApp1);
        em.persist(genre1);

        IndieApp indieApp2 = new IndieApp();
        indieApp2.setId(22L);
        indieApp2.setName("Hello");
        indieApp2.setHeaderImage("url2");
        em.persist(indieApp2);

        Genre genre2 = new Genre();
        genre2.setGenreId(22L);
        genre2.setDescription("인디");
        genre2.setIndieApp(indieApp2);
        em.persist(genre2);
    }

    @Test
    public void RouletteRecControllerTest() throws Exception {

        RouletteRecCriteriaDto request = new RouletteRecCriteriaDto();
        request.setGenreList(List.of("호러", "액션"));

        mockMvc.perform(post("/api/roulette-recommendation")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
