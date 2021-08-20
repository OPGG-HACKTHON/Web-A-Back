package opgg.weba.JamPick.repository;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.common.GetLocale;
import opgg.weba.JamPick.dto.RouletteRecDto;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Locale;

@Repository
@RequiredArgsConstructor
public class RouletteRecRepository {

    private final EntityManager em;

    public RouletteRecDto findOne(List<String> request) {

        Locale locale = GetLocale.getLocale();

        JpaResultMapper jpaResultMapper = new JpaResultMapper();

        String sql = "SELECT i.indie_app_id, i.name, i.header_image, group_concat(g.description separator ',') FROM indie_app AS i" +
                " JOIN genre AS g ON i.indie_app_id = g.indie_app_id" +
                " WHERE g.language = :locale AND i.indie_app_id IN (SELECT i.indie_app_id FROM genre WHERE description IN (:request) group by i.indie_app_id)" +
                " group by i.indie_app_id, i.name, i.header_image ORDER BY rand()";

        Query nativeQuery = em.createNativeQuery(sql)
                .setParameter("locale", locale)
                .setParameter("request", request)
                .setMaxResults(1);

        RouletteRecDto result = jpaResultMapper.uniqueResult(nativeQuery, RouletteRecDto.class);

        return result;
    }

}
