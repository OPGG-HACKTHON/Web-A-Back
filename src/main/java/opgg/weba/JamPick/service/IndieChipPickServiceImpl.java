package opgg.weba.JamPick.service;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.common.GetLocale;
import opgg.weba.JamPick.dto.IndieChipPickDto;
import opgg.weba.JamPick.repository.IndieAppRepository;
import opgg.weba.JamPick.repository.IndieChipPickDetailRepository;
import opgg.weba.JamPick.repository.IndieChipPickScheduler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndieChipPickServiceImpl implements IndieChipPickService {

    private final IndieChipPickDetailRepository indieChipPickDetailRepository;
    private final IndieAppRepository indieAppRepository;
    private final IndieChipPickScheduler indieChipPickScheduler;

    public List<IndieChipPickDto> getIndieChipPick() {

        String localeString = GetLocale.getLocale().toString();
        List<IndieChipPickDto> indieChipPickDtos = new ArrayList<>(2);

        List<Long> ongoingIndieChipPickAppDetailIds = indieChipPickDetailRepository.findOngoingIndieChipPickAppDetailIds();

        if (ongoingIndieChipPickAppDetailIds.isEmpty()) {
            indieChipPickScheduler.createNewVote();
            ongoingIndieChipPickAppDetailIds = indieChipPickDetailRepository.findOngoingIndieChipPickAppDetailIds();
        }

        for(int i = 0; i < 2; i++) {
            Long indieAppId = indieChipPickDetailRepository.findIndieAppIdById(ongoingIndieChipPickAppDetailIds.get(i));

            IndieChipPickDto indieChipPickDto = new IndieChipPickDto(
                    indieAppId,
                    indieAppRepository.findNameById(indieAppId),
                    indieChipPickDetailRepository.findLikeCountById(ongoingIndieChipPickAppDetailIds.get(i)),
                    indieAppRepository.findMovies(indieAppId),
                    indieAppRepository.findGenres(indieAppId, localeString)
            );
            indieChipPickDtos.add(indieChipPickDto);
        }

        return indieChipPickDtos;
    }
}
