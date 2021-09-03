package opgg.weba.JamPick.service;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.dto.RouletteRecRequestDto;
import opgg.weba.JamPick.dto.RouletteRecResponseDto;
import opgg.weba.JamPick.repository.RouletteRecRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RouletteRecService {

    private final RouletteRecRepository rouletteRecRepository;

    public RouletteRecResponseDto findRouletteApp(RouletteRecRequestDto request) {
        return rouletteRecRepository.findOne(request);
    }
}
