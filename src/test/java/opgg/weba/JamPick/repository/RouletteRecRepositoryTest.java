package opgg.weba.JamPick.repository;

import opgg.weba.JamPick.common.GetLocale;
import opgg.weba.JamPick.domain.Genre;
import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.dto.RouletteRecRequestDto;
import opgg.weba.JamPick.dto.RouletteRecResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class RouletteRecRepositoryTest {

    @Autowired RouletteRecRepository rouletteRecRepository;
    @Autowired EntityManager em;

    @BeforeEach
    public void before() {

        String localeString = GetLocale.getLocale().toString();

        IndieApp indieApp1 = new IndieApp();
        indieApp1.setId(11L);
        indieApp1.setName("App1");
        indieApp1.setHeaderImage("url1");
        em.persist(indieApp1);

        Genre genre1 = new Genre();
        genre1.setGenreId(11L);
        genre1.setLanguage(localeString);
        genre1.setDescription("액션");
        genre1.setIndieApp(indieApp1);
        em.persist(genre1);

        Genre genre2 = new Genre();
        genre2.setGenreId(22L);
        genre2.setLanguage(localeString);
        genre2.setDescription("RPG");
        genre2.setIndieApp(indieApp1);
        em.persist(genre2);

        Genre genre0 = new Genre();
        genre0.setGenreId(111L);
        genre0.setLanguage(localeString);
        genre0.setDescription("호러");
        genre0.setIndieApp(indieApp1);
        em.persist(genre0);

        IndieApp indieApp2 = new IndieApp();
        indieApp2.setId(22L);
        indieApp2.setName("App2");
        em.persist(indieApp2);

        Genre genre3 = new Genre();
        genre3.setGenreId(33L);
        genre3.setLanguage(localeString);
        genre3.setDescription("액션");
        genre3.setIndieApp(indieApp2);
        em.persist(genre3);

        Genre genre4 = new Genre();
        genre4.setGenreId(44L);
        genre4.setLanguage(localeString);
        genre4.setDescription("스포츠");
        genre4.setIndieApp(indieApp2);
        em.persist(genre4);
    }

    @Test
    public void RouletteRecRepositoryTest() {

        //given
        RouletteRecRequestDto rouletteRecRequestDto = new RouletteRecRequestDto();
        rouletteRecRequestDto.getGenres().add("스포츠");
        rouletteRecRequestDto.getGenres().add("액션");

        //when
        RouletteRecResponseDto rouletteApp = rouletteRecRepository.findOne(rouletteRecRequestDto);

        //then
        System.out.println("rouletteApp = " + rouletteApp.getGenres());
    }

}
