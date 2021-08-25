package opgg.weba.JamPick.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import opgg.weba.JamPick.dto.RouletteRecDto;
import opgg.weba.JamPick.repository.RouletteRecRepository;
import opgg.weba.JamPick.service.HomeViewService;
import opgg.weba.JamPick.service.RouletteRecService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static opgg.weba.JamPick.util.ApiDocumentUtils.getDocumentRequest;
import static opgg.weba.JamPick.util.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HomeViewController.class)
@AutoConfigureRestDocs(outputDir = "build/snippets")
public class HomeViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    RouletteRecRepository rouletteRecRepository;

    @MockBean
    RouletteRecService rouletteRecService;

    @MockBean
    HomeViewService homeViewService;

    @Test
    public void RouletteRecControllerTest() throws Exception {

        List<String> request = new ArrayList<>();
        request.add("스포츠");
        request.add("액션");

        given(rouletteRecService.findRouletteApp(request))
                .willReturn(
                        new RouletteRecDto(1L, "app1", "url1", List.of("액션", "RPG", "인디")));

        ResultActions result = mockMvc.perform(post("/api/roulette-recommendation")
                .content(objectMapper.writeValueAsString(request))
                        .locale(Locale.KOREA)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andDo(
                        document(
                                "HomeView",
                                getDocumentRequest(),
                                getDocumentResponse(),
//                                requestFields(
//                                        fieldWithPath("[]").description("장르 리스트")
//                                ), -> 스트링 타입이 안됨(org.springframework.restdocs.payload.PayloadHandlingException: Cannot handle application/json;charset=UTF-8 content as it could not be parsed as JSON or XML)
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
                .andDo(print());

    }
}
