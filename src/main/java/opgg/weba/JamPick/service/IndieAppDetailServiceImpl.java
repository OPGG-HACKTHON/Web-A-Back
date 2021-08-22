package opgg.weba.JamPick.service;

import opgg.weba.JamPick.common.GetLocale;
import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.domain.IndieAppDetail;
import opgg.weba.JamPick.dto.IndieAppDetailDto;
import opgg.weba.JamPick.exception.EntityType;
import opgg.weba.JamPick.exception.ExceptionType;
import opgg.weba.JamPick.exception.JampickException;
import opgg.weba.JamPick.repository.IndieAppDetailRepository;
import opgg.weba.JamPick.repository.IndieAppRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndieAppDetailServiceImpl implements IndieAppDetailService {

    @Autowired
    private IndieAppRepository indieAppRepository;

    @Autowired
    private IndieAppDetailRepository indieAppDetailRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public IndieAppDetailDto getIndieAppDetail(Long indieAppId) {
        String localeString = GetLocale.getLocale().toString();

        Optional<IndieAppDetail> indieAppDetailOptional = indieAppDetailRepository.findByIndieApp_IdAndLanguage(indieAppId, localeString);

        if (indieAppDetailOptional.isPresent()) {
            IndieAppDetail indieAppDetail = indieAppDetailOptional.get();
            IndieApp indieApp = indieAppDetail.getIndieApp();

            IndieAppDetailDto indieAppDetailDto = modelMapper.map(indieAppDetail, IndieAppDetailDto.class);
            indieAppDetailDto.setHeaderImage(indieApp.getHeaderImage());
            indieAppDetailDto.setName(indieApp.getName());

            indieAppDetailDto.setGenres(indieAppRepository.findGenres(indieAppId, localeString));
            indieAppDetailDto.setMovies(indieAppRepository.findMovies(indieAppId));
            indieAppDetailDto.setScreenshots(indieAppRepository.findScreenshots(indieAppId));

            return indieAppDetailDto;
        }

        throw JampickException.throwException(EntityType.INDIE_APP_DETAIL, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(indieAppId));
    }
}
