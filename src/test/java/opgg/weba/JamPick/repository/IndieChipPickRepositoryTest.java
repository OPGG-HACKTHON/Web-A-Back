package opgg.weba.JamPick.repository;

import opgg.weba.JamPick.common.GetLocale;
import opgg.weba.JamPick.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
public class IndieChipPickRepositoryTest {

    @Autowired EntityManager em;
    @Autowired IndieChipPickDetailRepository indieChipPickDetailRepository;
    @Autowired IndieAppRepository indieAppRepository;
    @Autowired IndieChipPickScheduler indieChipPickScheduler;

    @BeforeEach
    public void before() {

        IndieApp indieApp1 = new IndieApp();
        indieApp1.setId(1L);
        indieApp1.setName("App1");
        indieApp1.setHeaderImage("asdf");
        em.persist(indieApp1);

        Genre genre1 = new Genre();
        genre1.setGenreId(1L);
        genre1.setLanguage("ko_KR");
        genre1.setDescription("Action");
        genre1.setIndieApp(indieApp1);
        em.persist(genre1);

        Genre genre20 = new Genre();
        genre20.setGenreId(2L);
        genre20.setLanguage("ko_KR");
        genre20.setDescription("RPG");
        genre20.setIndieApp(indieApp1);
        em.persist(genre20);

        Genre genre0 = new Genre();
        genre0.setGenreId(0L);
        genre0.setLanguage("ko_KR");
        genre0.setDescription("호러");
        genre0.setIndieApp(indieApp1);
        em.persist(genre0);

        Movie movie0 = new Movie();
        movie0.setMovieId(1L);
        movie0.setMp4("asdf");
        movie0.setIndieApp(indieApp1);
        em.persist(movie0);

        IndieApp indieApp2 = new IndieApp();
        indieApp2.setId(2L);
        indieApp2.setName("App2");
        indieApp2.setHeaderImage("fdas");
        em.persist(indieApp2);

        Genre genre3 = new Genre();
        genre3.setGenreId(3L);
        genre3.setLanguage("ko_KR");
        genre3.setDescription("FPS");
        genre3.setIndieApp(indieApp2);
        em.persist(genre3);

        Genre genre4 = new Genre();
        genre4.setGenreId(4L);
        genre4.setLanguage("ko_KR");
        genre4.setDescription("Sport");
        genre4.setIndieApp(indieApp2);
        em.persist(genre4);

        Movie movie1 = new Movie();
        movie1.setMovieId(2L);
        movie1.setMp4("qrewqr");
        movie1.setIndieApp(indieApp2);
        em.persist(movie1);

        IndieChipPick indieChipPick1 = new IndieChipPick();
        indieChipPick1.setIsVoteEnd(false);
        em.persist(indieChipPick1);

        IndieChipPickAppDetail indieChipPickAppDetail1 = new IndieChipPickAppDetail();
        indieChipPickAppDetail1.setLikeCount(10);
        indieChipPickAppDetail1.setIndieChipPick(indieChipPick1);
        indieChipPickAppDetail1.setIndieApp(indieApp1);
        em.persist(indieChipPickAppDetail1);

        IndieChipPickAppDetail indieChipPickAppDetail2 = new IndieChipPickAppDetail();
        indieChipPickAppDetail2.setLikeCount(20);
        indieChipPickAppDetail2.setIndieChipPick(indieChipPick1);
        indieChipPickAppDetail2.setIndieApp(indieApp2);
        em.persist(indieChipPickAppDetail2);

    }

    @Test
    public void IndieChipPickRepositoryTest() {
        //given
        String localeString = GetLocale.getLocale().toString();

        //when
        List<Long> ongoingIndieChipPickAppDetailIds = indieChipPickDetailRepository.findOngoingIndieChipPickAppDetailIds();

        Long indieAppId = indieChipPickDetailRepository.findIndieAppIdById(ongoingIndieChipPickAppDetailIds.get(0));
        String indieAppName = indieAppRepository.findNameById(indieAppId);
        Integer likeCount = indieChipPickDetailRepository.findLikeCountById(ongoingIndieChipPickAppDetailIds.get(0));
        List<String> movies = indieAppRepository.findMovies(indieAppId);
        List<String> genres = indieAppRepository.findGenres(indieAppId, localeString);

        //then
        System.out.println("ongoingIndieChipPickAppDetailId = " + ongoingIndieChipPickAppDetailIds.get(0));
        System.out.println("indieAppId = " + indieAppId);
        System.out.println("indieAppName = " + indieAppName);
        System.out.println("likeCount = " + likeCount);
        System.out.println("movies = " + movies);
        System.out.println("genres = " + genres);

    }
}