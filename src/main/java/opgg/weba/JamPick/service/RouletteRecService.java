package opgg.weba.JamPick.service;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.dto.RouletteRecDTO;
import opgg.weba.JamPick.repository.RouletteRecRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RouletteRecService {

    private final RouletteRecRepository randomRecRepository;

    @Transactional
    public RouletteRecDTO findRouletteApp() {
        return randomRecRepository.findOne();
    }
}
