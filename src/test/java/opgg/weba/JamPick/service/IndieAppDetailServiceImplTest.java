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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class IndieAppDetailServiceImplTest {

    @Autowired
    private IndieAppDetailServiceImpl indieAppDetailService;

    @Autowired
    private IndieAppRepository indieAppRepository;

    @Autowired
    EntityManager em;


    @BeforeEach
    void setUp() {
        Util.createIndieApp(indieAppRepository, 1L);
    }

    @Test
    void testGetIndieAppDetail() {
        // given
        IndieApp indieApp = indieAppRepository.getById(1L);

        Util.createIndieAppDetail(em, indieApp);

        // when
        IndieAppDetailDto indieAppDetailDto = indieAppDetailService.getIndieAppDetail(indieApp.getId());

        // then
        Locale locale = GetLocale.getLocale();
        IndieAppDetail expectIndieAppDetail = null;

        for (IndieAppDetail indieAppDetail : indieApp.getIndieAppDetails()) {
            if (indieAppDetail.getLanguage().equals(locale.toString())) {
                expectIndieAppDetail = indieAppDetail;
            }
        }

        assert expectIndieAppDetail != null;
        assertThat(indieAppDetailDto.getId()).isEqualTo(expectIndieAppDetail.getId());

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> indieAppDetailService.getIndieAppDetail(200L));
        assertThat(runtimeException.getMessage()).isEqualTo(
                JampickException.throwException(EntityType.INDIE_APP_DETAIL, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(200L)).getMessage()
        );
    }
}