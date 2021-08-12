package opgg.weba.JamPick.repository;

import opgg.weba.JamPick.domain.Genre;
import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.dto.RouletteRecCriteriaDto;
import opgg.weba.JamPick.dto.RouletteRecDto;
import org.assertj.core.api.Assertions;
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

    @PersistenceContext
    EntityManager em;

    @Autowired RouletteRecRepository rouletteRecRepository;

    @Test
    public void 룰렛추천() {

        IndieApp indieApp = new IndieApp();
        indieApp.setId(1L);
        indieApp.setName("Hi");
        indieApp.setHeaderImage("Image_URL");
        em.persist(indieApp);

        Genre genre = new Genre();
        genre.setGenreId(11L);
        genre.setDescription("호러");
        genre.setIndieApp(indieApp);
        em.persist(genre);

        RouletteRecCriteriaDto request = new RouletteRecCriteriaDto();
        request.setGenreList(List.of("호러"));

        RouletteRecDto rouletteApp = rouletteRecRepository.findOne(request);

        Assertions.assertThat(rouletteApp.getHeader_image()).isEqualTo("Image_URL");
    }

}
