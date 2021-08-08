package opgg.weba.JamPick.repository;

import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.dto.RouletteRecDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
public class RouletteRecRepositoryTest {

//    @PersistenceContext
//    EntityManager em;
//
//    @Autowired RouletteRecRepository rouletteRecRepository;
//
//    @Test
//    public void 룰렛추천() {
//
//        IndieApp indieApp = new IndieApp();
//        indieApp.setId(1L);
//        indieApp.setName("Hi");
//        indieApp.setHeader_image("Image_URL");
//
//        em.persist(indieApp);
//
//        RouletteRecDTO rouletteApp = rouletteRecRepository.findOne();
//
//        Assertions.assertThat(rouletteApp.getHeader_image()).isEqualTo(indieApp.getHeader_image());
//    }

}
