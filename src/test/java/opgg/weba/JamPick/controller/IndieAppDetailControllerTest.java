
package opgg.weba.JamPick.controller;

import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.domain.IndieAppDetail;
import opgg.weba.JamPick.repository.IndieAppDetailRepository;
import opgg.weba.JamPick.repository.IndieAppRepository;
import opgg.weba.JamPick.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class IndieAppDetailControllerTest {

    @Autowired
    IndieAppRepository indieAppRepository;

    @Autowired
    IndieAppDetailRepository indieAppDetailRepository;

    @Autowired
    EntityManager em;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        IndieApp indieApp = Util.createIndieApp(indieAppRepository, 1L);

        Util.createIndieAppDetail(em, indieApp);

        Util.createGenres(em, indieApp, 3);

        Util.createMovies(em, indieApp, 3);

        Util.createScreenshots(em, indieApp, 3);
    }

    @Test
    void getIndieAppDetail() throws Exception {
        Optional<IndieAppDetail> indieAppDetailOptional = indieAppDetailRepository.findByIndieApp_IdAndLanguage(1L, Locale.KOREA.toString());

        assertThat(indieAppDetailOptional).isPresent();

        IndieAppDetail indieAppDetail = indieAppDetailOptional.get();

        mockMvc.perform(
                        get("/api/detail/1")
                                .locale(Locale.KOREA)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(equalTo(indieAppDetail.getId().intValue())))
                .andExpect(jsonPath("$.data.name").value(equalTo(indieAppDetail.getIndieApp().getName())))
                .andExpect(jsonPath("$.data.shortDescription").value(equalTo(indieAppDetail.getShortDescription())))
                .andExpect(jsonPath("$.data.headerImage").value(equalTo(indieAppDetail.getIndieApp().getHeaderImage())))
                .andExpect(jsonPath("$.data.releaseDate").value(equalTo(indieAppDetail.getReleaseDate())))
                .andExpect(jsonPath("$.data.genres[0]").exists())
                .andExpect(jsonPath("$.data.genres[1]").exists())
                .andExpect(jsonPath("$.data.genres[2]").exists())
                .andExpect(jsonPath("$.data.movies[0]").exists())
                .andExpect(jsonPath("$.data.movies[1]").exists())
                .andExpect(jsonPath("$.data.movies[2]").exists())
                .andExpect(jsonPath("$.data.screenshots[0]").exists())
                .andExpect(jsonPath("$.data.screenshots[1]").exists())
                .andExpect(jsonPath("$.data.screenshots[2]").exists());


    }
}