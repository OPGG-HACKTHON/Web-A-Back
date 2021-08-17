package opgg.weba.JamPick.repository;

import opgg.weba.JamPick.domain.Genre;
import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.dto.RouletteRecCriteriaDto;
import opgg.weba.JamPick.dto.RouletteRecDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
public class RouletteRecRepositoryTest {

    @Autowired RouletteRecRepository rouletteRecRepository;
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
    public void RouletteRecRepositoryTest() {

        //given
        RouletteRecCriteriaDto request = new RouletteRecCriteriaDto();
        request.setGenreList(List.of("호러", "액션"));

        //when
        RouletteRecDto rouletteApp = rouletteRecRepository.findOne(request);

        //then
        Assertions.assertThat(rouletteApp.getHeader_image()).isEqualTo("url1");
    }

}
