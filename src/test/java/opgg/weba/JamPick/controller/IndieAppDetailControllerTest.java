package opgg.weba.JamPick.controller;

import opgg.weba.JamPick.common.ResponseCode;
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
import static org.hamcrest.Matchers.nullValue;
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
                                pathParameters(parameterWithName("id").description("?????? ??? id")),
                                responseFields(
                                        fieldWithPath("code").type(JsonFieldType.NUMBER).description("??????"),
                                        fieldWithPath("response_message").type(JsonFieldType.STRING).description("?????? ?????????"),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("?????? ??? ?????? ?????????"),
                                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("?????? ??? ??????"),
                                        fieldWithPath("data.short_description").type(JsonFieldType.STRING).description("?????? ??? ???"),
                                        fieldWithPath("data.release_date").type(JsonFieldType.STRING).description("?????? ??? ?????????"),
                                        fieldWithPath("data.header_image").type(JsonFieldType.STRING).description("?????? ??? ?????? ?????????"),
                                        fieldWithPath("data.genres.[]").type(JsonFieldType.ARRAY).description("?????? ??? ?????? ?????????"),
                                        fieldWithPath("data.movies.[]").type(JsonFieldType.ARRAY).description("?????? ??? ?????? ?????????"),
                                        fieldWithPath("data.screenshots.[]").type(JsonFieldType.ARRAY).description("?????? ??? ???????????? ?????????")
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

    @Test
    void getIndieAppDetailNotFoundException() throws Exception {
        mockMvc.perform(
                RestDocumentationRequestBuilders.get("/api/detail/{id}", 100000)
                        .locale(Locale.KOREA)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(ResponseCode.NOT_FOUND.getValue()))
                .andExpect(jsonPath("$.data").value(nullValue()));

        mockMvc.perform(
                RestDocumentationRequestBuilders.get("/api/detail/{id}", 1)
                        .locale(Locale.CHINA)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(ResponseCode.NOT_FOUND.getValue()))
                .andExpect(jsonPath("$.data").value(nullValue()));
    }
}