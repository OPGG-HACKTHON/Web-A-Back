package opgg.weba.JamPick.repository;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.dto.RouletteRecDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RouletteRecRepository {

    private final EntityManager em;

    public RouletteRecDTO findOne() {
        TypedQuery<RouletteRecDTO> query = em.createQuery("select new opgg.weba.JamPick.dto.RouletteRecDTO(i.id, i.header_image) from IndieApp i", RouletteRecDTO.class);
        RouletteRecDTO result = query.getSingleResult();

        return result;
    }

}
