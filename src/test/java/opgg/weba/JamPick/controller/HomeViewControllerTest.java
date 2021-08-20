package opgg.weba.JamPick.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import opgg.weba.JamPick.repository.RouletteRecRepository;
import opgg.weba.JamPick.service.HomeViewService;
import opgg.weba.JamPick.service.RouletteRecService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HomeViewController.class)
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

        mockMvc.perform(post("/api/roulette-recommendation")
                .content(objectMapper.writeValueAsString(request))
                        .locale(Locale.KOREA)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse();
    }
}
