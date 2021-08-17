package opgg.weba.JamPick.service;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.dto.RandomRecDto;
import opgg.weba.JamPick.repository.RandomRecRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RandomRecService {

    private final RandomRecRepository randomRecRepository;

    public List<RandomRecDto> findRandomApps() {
        return randomRecRepository.findRandomApps();
    }
}
