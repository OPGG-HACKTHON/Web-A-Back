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

    @Autowired RouletteRecRepository rouletteRecRepository;

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
