package opgg.weba.JamPick.repository;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.dto.RandomRecDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RandomRecRepository {

    private final EntityManager em;

    public List<RandomRecDto> findRandomApps() {
        return em.createQuery(
                "select new opgg.weba.JamPick.dto.RandomRecDto(i.id, i.name, i.isFree, g.description)" +
                " from IndieApp i join i.genres g", RandomRecDto.class) //TODO -> description 배열로 받게 변경
                .setMaxResults(12)
                .getResultList();
    }
}

