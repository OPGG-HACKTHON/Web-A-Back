package opgg.weba.JamPick.repository;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.dto.RouletteRecCriteriaDto;
import opgg.weba.JamPick.dto.RouletteRecDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RouletteRecRepository {

    private final EntityManager em;

    public RouletteRecDto findOne(RouletteRecCriteriaDto request) {

        return  em.createQuery(
                "select new opgg.weba.JamPick.dto.RouletteRecDto(i.id, i.headerImage)" +
                        " from IndieApp i join i.genres g where g.description in :request", RouletteRecDto.class)
                .setParameter("request", request.getGenreList())
                .getSingleResult();
    }

}
