package opgg.weba.JamPick.repository;

import opgg.weba.JamPick.domain.Genre;
import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.dto.RandomRecDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
public class RandomRecRepositoryTest {
    
    @Autowired RandomRecRepository randomRecRepository;
    @Autowired EntityManager em;

    @BeforeEach
    public void before() {

        IndieApp indieApp1 = new IndieApp();
        indieApp1.setId(11L);
        indieApp1.setName("App1");
        em.persist(indieApp1);

        Genre genre1 = new Genre();
        genre1.setGenreId(11L);
        genre1.setLanguage("ko_KR");
        genre1.setDescription("Action");
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
        genre3.setDescription("FPS");
        genre3.setIndieApp(indieApp2);
        em.persist(genre3);

        Genre genre4 = new Genre();
        genre4.setGenreId(44L);
        genre4.setLanguage("ko_KR");
        genre4.setDescription("Sport");
        genre4.setIndieApp(indieApp2);
        em.persist(genre4);
    }
    
    @Test
    public void RandomRecRepositoryTest() {
        //given
        
        //when
        List<RandomRecDto> result = randomRecRepository.findRandomApps();
        
        //then
        Assertions.assertThat(result.get(0).getName()).isEqualTo("App1");
    }


}
