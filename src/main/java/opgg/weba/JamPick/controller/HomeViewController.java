package opgg.weba.JamPick.controller;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.dto.ResponseDto;
import opgg.weba.JamPick.dto.RouletteRecCriteriaDto;
import opgg.weba.JamPick.dto.RouletteRecDto;
import opgg.weba.JamPick.service.RouletteRecService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeViewController {

    private final RouletteRecService rouletteRecService;

    @PostMapping(value = "/roulette-recommendation")
    public ResponseEntity getRouletteApp(@RequestBody RouletteRecCriteriaDto request) {

        RouletteRecDto result = rouletteRecService.findRouletteApp(request);

        ResponseDto responseDto = ResponseDto.builder()
                .status(200)
                .responseMessage("룰렛 인디게임 조회 성공")
                .data(result)
                .build();

        return ResponseEntity.ok(responseDto);
    }
}
