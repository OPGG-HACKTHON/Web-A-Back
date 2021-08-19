package opgg.weba.JamPick.service;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.dto.RouletteRecDto;
import opgg.weba.JamPick.repository.RouletteRecRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RouletteRecService {

    private final RouletteRecRepository rouletteRecRepository;

    @Transactional
    public RouletteRecDto findRouletteApp(List<String> request) {
        return rouletteRecRepository.findOne(request);
    }
}
