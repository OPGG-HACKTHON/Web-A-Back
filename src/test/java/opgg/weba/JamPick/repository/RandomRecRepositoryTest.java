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
        indieApp1.setId(1L);
        indieApp1.setName("App1");
        em.persist(indieApp1);

        Genre genre1 = new Genre();
        genre1.setGenreId(1L);
        genre1.setDescription("Action");
        genre1.setIndieApp(indieApp1);
        em.persist(genre1);
        
        Genre genre2 = new Genre();
        genre2.setGenreId(2L);
        genre2.setDescription("RPG");
        genre2.setIndieApp(indieApp1);
        em.persist(genre2);

        IndieApp indieApp2 = new IndieApp();
        indieApp2.setId(2L);
        indieApp2.setName("App2");
        em.persist(indieApp2);

        Genre genre3 = new Genre();
        genre3.setGenreId(3L);
        genre3.setDescription("FPS");
        genre3.setIndieApp(indieApp2);
        em.persist(genre3);

        Genre genre4 = new Genre();
        genre4.setGenreId(4L);
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
        for (RandomRecDto randomRecDto : result) { //TODO -> 검증으로 변경
            System.out.println("randomRecDto = " + randomRecDto);
        }
    }


}
