package opgg.weba.JamPick.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import opgg.weba.JamPick.domain.Genre;
import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.repository.IndieAppRepository;
import opgg.weba.JamPick.service.RouletteRecService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static opgg.weba.JamPick.util.ApiDocumentUtils.getDocumentRequest;
import static opgg.weba.JamPick.util.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@AutoConfigureRestDocs()
public class HomeViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EntityManager em;

    @Autowired
    RouletteRecService rouletteRecService;

    @Autowired
    IndieAppRepository indieAppRepository;

    @BeforeEach
    void setUp() {

        IndieApp indieApp1 = new IndieApp();
        indieApp1.setId(11L);
        indieApp1.setName("App1");
        indieApp1.setIsFree(false);
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
        indieApp2.setIsFree(false);
        indieApp2.setHeaderImage("url2");
        em.persist(indieApp2);

        Genre genre3 = new Genre();
        genre3.setGenreId(33L);
        genre3.setLanguage("ko_KR");
        genre3.setDescription("스포츠");
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
    public void GetHomeApiTest() throws Exception {

        mockMvc.perform(
                RestDocumentationRequestBuilders.get("/api/home")
                        .locale(Locale.KOREA)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(
                        document(
                                "HomeView",
                                getDocumentRequest(),
                                getDocumentResponse(),
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.NUMBER).description("상태 코드"),
                                        fieldWithPath("response_message").type(JsonFieldType.STRING).description("응답 메시지"),
                                        fieldWithPath("data.random_rec_list.[].id").type(JsonFieldType.NUMBER).description("인디 앱 아이디"),
                                        fieldWithPath("data.random_rec_list.[].name").type(JsonFieldType.STRING).description("인디 앱 이름"),
                                        fieldWithPath("data.random_rec_list.[].is_free").type(JsonFieldType.BOOLEAN).description("인디 앱 무료 여부"),
                                        fieldWithPath("data.random_rec_list.[].header_image").type(JsonFieldType.STRING).description("인디 앱 헤더 이미지"),
                                        fieldWithPath("data.random_rec_list.[].genres.[]").type(JsonFieldType.ARRAY).description("인디 앱 장르 리스트")
                                )
                        )
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.random_rec_list.id").exists())
                .andExpect(jsonPath("$.data.random_rec_list.name").exists())
                .andExpect(jsonPath("$.data.random_rec_list.is_free").exists())
                .andExpect(jsonPath("$.data.random_rec_list.header_image").exists())
                .andExpect(jsonPath("$.data.random_rec_list.genres").exists());
    }

    @Test
    public void GetRouletteAppApiTest() throws Exception {

        List<String> request = new ArrayList<>();
        request.add("스포츠");
        request.add("액션");

        mockMvc.perform(
                RestDocumentationRequestBuilders.post("/api/roulette-recommendation")
                        .content(objectMapper.writeValueAsString(request))
                        .locale(Locale.KOREA)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(
                        document(
                                "RouletteRecommendation",
                                getDocumentRequest(),
                                getDocumentResponse(),
                                requestFields(
                                        fieldWithPath("[]").description("유저가 선택한 장르 리스트")
                                ),
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.NUMBER).description("상태 코드"),
                                        fieldWithPath("response_message").type(JsonFieldType.STRING).description("응답 메시지"),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("인디 앱 아이디"),
                                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("인디 앱 이름"),
                                        fieldWithPath("data.header_image").type(JsonFieldType.STRING).description("인디 앱 헤더 이미지"),
                                        fieldWithPath("data.genres.[]").type(JsonFieldType.ARRAY).description("인디 앱 장르 리스트")
                                )
                        )
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.name").exists())
                .andExpect(jsonPath("$.data.header_image").exists())
                .andExpect(jsonPath("$.data.genres").exists());
    }
}
