package opgg.weba.JamPick.controller;

import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.domain.IndieAppDetail;
import opgg.weba.JamPick.repository.IndieAppDetailRepository;
import opgg.weba.JamPick.repository.IndieAppRepository;
import opgg.weba.JamPick.util.Util;
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
import java.util.Locale;
import java.util.Optional;

import static opgg.weba.JamPick.util.ApiDocumentUtils.getDocumentRequest;
import static opgg.weba.JamPick.util.ApiDocumentUtils.getDocumentResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@AutoConfigureRestDocs()
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
        IndieApp indieApp = indieAppRepository.save(Util.createIndieApp(indieAppRepository, 1L));

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
                RestDocumentationRequestBuilders.get("/api/detail/{id}", 1)
                        .locale(Locale.KOREA)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(
                        document(
                                "IndieAppDetail",
                                getDocumentRequest(),
                                getDocumentResponse(),
                                pathParameters(parameterWithName("id").description("인디 앱 id")),
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.NUMBER).description("상태 코드"),
                                        fieldWithPath("response_message").type(JsonFieldType.STRING).description("응답 메시지"),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("인디 앱 상세 아이디"),
                                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("인디 앱 이름"),
                                        fieldWithPath("data.short_description").type(JsonFieldType.STRING).description("인디 앱 설"),
                                        fieldWithPath("data.release_date").type(JsonFieldType.STRING).description("인디 앱 출시일"),
                                        fieldWithPath("data.header_image").type(JsonFieldType.STRING).description("인디 앱 헤더 이미지"),
                                        fieldWithPath("data.genres.[]").type(JsonFieldType.ARRAY).description("인디 앱 장르 리스트"),
                                        fieldWithPath("data.movies.[]").type(JsonFieldType.ARRAY).description("인디 앱 영상 리스트"),
                                        fieldWithPath("data.screenshots.[]").type(JsonFieldType.ARRAY).description("인디 앱 스크린샷 리스트")
                                )
                        )
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(equalTo(indieAppDetail.getId().intValue())))
                .andExpect(jsonPath("$.data.name").value(equalTo(indieAppDetail.getIndieApp().getName())))
                .andExpect(jsonPath("$.data.short_description").value(equalTo(indieAppDetail.getShortDescription())))
                .andExpect(jsonPath("$.data.header_image").value(equalTo(indieAppDetail.getIndieApp().getHeaderImage())))
                .andExpect(jsonPath("$.data.release_date").value(equalTo(indieAppDetail.getReleaseDate())))
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