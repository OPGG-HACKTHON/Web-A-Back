package opgg.weba.JamPick.repository;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.common.GetLocale;
import opgg.weba.JamPick.dto.RouletteRecRequestDto;
import opgg.weba.JamPick.dto.RouletteRecResponseDto;
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

    public RouletteRecResponseDto findOne(RouletteRecRequestDto request) {

        List<String> genreList = request.getGenres();
        Locale locale = GetLocale.getLocale();

        JpaResultMapper jpaResultMapper = new JpaResultMapper();

        String sql;
        Query nativeQuery;

        if(genreList.isEmpty()) {

            sql = "SELECT i.indie_app_id, i.name, i.header_image, group_concat(g.description separator ',') FROM indie_app AS i" +
                    " JOIN genre AS g ON i.indie_app_id = g.indie_app_id" +
                    " WHERE g.language = :locale" +
                    " group by i.indie_app_id, i.name, i.header_image ORDER BY rand()";

            nativeQuery = em.createNativeQuery(sql)
                    .setParameter("locale", locale)
                    .setMaxResults(1);
        } else {

            sql = "SELECT i.indie_app_id, i.name, i.header_image, group_concat(g.description separator ',') FROM indie_app AS i" +
                    " JOIN genre AS g ON i.indie_app_id = g.indie_app_id" +
                    " WHERE g.language = :locale AND i.indie_app_id IN (SELECT g.indie_app_id FROM genre AS g WHERE g.description IN (:genreList))" +
                    " group by i.indie_app_id, i.name, i.header_image ORDER BY rand()";

            nativeQuery = em.createNativeQuery(sql)
                    .setParameter("locale", locale)
                    .setParameter("genreList", genreList)
                    .setMaxResults(1);
        }

        RouletteRecResponseDto result = jpaResultMapper.uniqueResult(nativeQuery, RouletteRecResponseDto.class);

        return result;
    }

}
