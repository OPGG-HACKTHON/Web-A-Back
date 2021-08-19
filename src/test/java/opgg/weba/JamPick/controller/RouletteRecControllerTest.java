package opgg.weba.JamPick.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import opgg.weba.JamPick.domain.Genre;
import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.repository.RouletteRecRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        indieApp1.setName("App1");
        indieApp1.setHeaderImage("url1");
        em.persist(indieApp1);

        Genre genre1 = new Genre();
        genre1.setGenreId(11L);
        genre1.setLanguage("ko_KR");
        genre1.setDescription("액션");
        genre1.setIndieApp(indieApp1);
        em.persist(genre1);

        Genre genre2 = new Genre();
        genre2.setGenreId(22L);
        genre2.setLanguage("ko_KR");
        genre2.setDescription("RPG");
        genre2.setIndieApp(indieApp1);
        em.persist(genre2);

        Genre genre0 = new Genre();
        genre0.setGenreId(111L);
        genre0.setLanguage("ko_KR");
        genre0.setDescription("호러");
        genre0.setIndieApp(indieApp1);
        em.persist(genre0);

        IndieApp indieApp2 = new IndieApp();
        indieApp2.setId(22L);
        indieApp2.setName("App2");
        em.persist(indieApp2);

        Genre genre3 = new Genre();
        genre3.setGenreId(33L);
        genre3.setLanguage("ko_KR");
        genre3.setDescription("액션");
        genre3.setIndieApp(indieApp2);
        em.persist(genre3);

        Genre genre4 = new Genre();
        genre4.setGenreId(44L);
        genre4.setLanguage("ko_KR");
        genre4.setDescription("스포츠");
        genre4.setIndieApp(indieApp2);
        em.persist(genre4);
    }

    @Test
    public void RouletteRecControllerTest() throws Exception {

        List<String> request = new ArrayList<>();
        request.add("스포츠");
        request.add("액션");

        mockMvc.perform(post("/api/roulette-recommendation")
                .content(objectMapper.writeValueAsString(request))
                        .locale(Locale.KOREA)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse();
    }
}
