package opgg.weba.JamPick.service;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.dto.HomeViewDto;
import opgg.weba.JamPick.dto.RandomRecDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeViewService {

    private final RandomRecService randomRecService;

    public HomeViewDto getHomeData() {

        HomeViewDto homeViewDto = new HomeViewDto();

        List<RandomRecDto> randomRecDto = randomRecService.findRandomApps();

        return homeViewDto; //Temp
    }
}
