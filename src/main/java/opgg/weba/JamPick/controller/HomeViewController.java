package opgg.weba.JamPick.controller;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.dto.ResponseDTO;
import opgg.weba.JamPick.dto.RouletteRecDTO;
import opgg.weba.JamPick.service.RouletteRecService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeViewController {

    private final RouletteRecService rouletteRecService;

    @GetMapping(value = "/api/roulette-recommendation")
    public ResponseEntity getRouletteApp() {

        RouletteRecDTO result = rouletteRecService.findRouletteApp();

        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(200)
                .responseMessage("룰렛 인디게임 조회 성공")
                .data(result)
                .build();

        return ResponseEntity.ok(responseDTO);
    }
}
