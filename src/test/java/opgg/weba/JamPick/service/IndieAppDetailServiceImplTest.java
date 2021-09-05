package opgg.weba.JamPick.service;

import opgg.weba.JamPick.common.GetLocale;
import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.domain.IndieAppDetail;
import opgg.weba.JamPick.dto.IndieAppDetailDto;
import opgg.weba.JamPick.exception.EntityType;
import opgg.weba.JamPick.exception.ExceptionType;
import opgg.weba.JamPick.exception.JampickException;
import opgg.weba.JamPick.repository.IndieAppRepository;
import opgg.weba.JamPick.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class IndieAppDetailServiceImplTest {

    @Autowired
    EntityManager em;
    @Autowired
    private IndieAppDetailServiceImpl indieAppDetailService;
    @Autowired
    private IndieAppRepository indieAppRepository;
    private List<IndieAppDetail> indieAppDetails;

    @BeforeEach
    void setUp() {
        IndieApp indieApp = indieAppRepository.save(Util.createIndieApp(indieAppRepository, 1L));

        indieAppDetails = Util.createIndieAppDetail(em, indieApp);

        Util.createGenres(em, indieApp, 3);

        Util.createMovies(em, indieApp, 3);

        Util.createScreenshots(em, indieApp, 3);
    }

    @Test
    void testGetIndieAppDetail() {
        // given

        // when
        LocaleContextHolder.setLocale(Locale.KOREA);
        IndieAppDetailDto indieAppDetailDto = indieAppDetailService.getIndieAppDetail(1L);

        // then
        Locale locale = GetLocale.getLocale();
        IndieAppDetail expectIndieAppDetail = null;

        for (IndieAppDetail indieAppDetail : indieAppDetails) {
            if (indieAppDetail.getLanguage().equals(locale.toString())) {
                expectIndieAppDetail = indieAppDetail;
            }
        }

        assert expectIndieAppDetail != null;
        assertThat(indieAppDetailDto.getId()).isEqualTo(expectIndieAppDetail.getId());

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> indieAppDetailService.getIndieAppDetail(200L));
        assertThat(runtimeException.getMessage()).isEqualTo(
                JampickException.throwException(EntityType.INDIE_APP_DETAIL, ExceptionType.ENTITY_NOT_FOUND, String.format("Game Id: %s, Language: %s", 200L, locale.toString())).getMessage()
        );
    }
}