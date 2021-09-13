package opgg.weba.JamPick.service;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.dto.HomeViewDto;
import opgg.weba.JamPick.dto.IndieChipPickDto;
import opgg.weba.JamPick.dto.RandomRecDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional()
@RequiredArgsConstructor
public class HomeViewService {

    private final RandomRecService randomRecService;

    private final IndieChipPickService indieChipPickService;

    public HomeViewDto getHomeData() {

        List<RandomRecDto> randomRecDto = randomRecService.findRandomApps();
        List<IndieChipPickDto> indieChipPickDto = indieChipPickService.getIndieChipPick();

        HomeViewDto homeViewDto = new HomeViewDto(randomRecDto, indieChipPickDto);

        return homeViewDto;
    }
}
