package opgg.weba.JamPick.repository;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.common.GetLocale;
import opgg.weba.JamPick.dto.RandomRecDto;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Locale;

@Repository
@RequiredArgsConstructor
public class RandomRecRepository {

    private final EntityManager em;

    public List<RandomRecDto> findRandomApps() {

        Locale locale = GetLocale.getLocale();

        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        String sql = "SELECT i.indie_app_id, i.name, i.is_free, i.header_image, group_concat(g.description separator ',') FROM indie_app AS i" +
                " JOIN genre AS g ON i.indie_app_id = g.indie_app_id WHERE g.language = :locale" +
                " group by i.indie_app_id, i.name";

        Query nativeQuery = em.createNativeQuery(sql).setParameter("locale", locale).setMaxResults(12);
        List<RandomRecDto> results = jpaResultMapper.list(nativeQuery, RandomRecDto.class);

        return results;
    }
}
