package opgg.weba.JamPick.controller;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.dto.HomeViewDto;
import opgg.weba.JamPick.dto.ResponseDto;
import opgg.weba.JamPick.dto.RouletteRecRequestDto;
import opgg.weba.JamPick.dto.RouletteRecResponseDto;
import opgg.weba.JamPick.service.HomeViewService;
import opgg.weba.JamPick.service.RouletteRecService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
//@ControllerAdvice
@RequiredArgsConstructor
public class HomeViewController {

    private final RouletteRecService rouletteRecService;
    private final HomeViewService homeViewService;

//    @ExceptionHandler(value = Exception.class)
//    public Map<String, String> handleException(Exception e) {
//        Map<String, String> map = new HashMap<>();
//        map.put("errMsg", e.getMessage());
//        return map;
//    }

    @PostMapping(value = "/roulette-recommendation")
    public ResponseEntity getRouletteApp(@RequestBody RouletteRecRequestDto request) {

        RouletteRecResponseDto result = rouletteRecService.findRouletteApp(request);

        ResponseDto responseDto = ResponseDto.builder()
                .code(0)
                .responseMessage("룰렛 인디게임 조회 성공")
                .data(result)
                .build();

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "/home")
    public ResponseEntity getHome() {

        HomeViewDto result = homeViewService.getHomeData();

        ResponseDto responseDto = ResponseDto.builder()
                .code(0)
                .responseMessage("홈 뷰 데이터 조회 성공")
                .data(result)
                .build();

        return ResponseEntity.ok(responseDto);
    }
}
