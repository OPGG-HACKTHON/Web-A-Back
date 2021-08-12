package opgg.weba.JamPick.repository;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.dto.RouletteRecCriteriaDto;
import opgg.weba.JamPick.dto.RouletteRecDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
@RequiredArgsConstructor
public class RouletteRecRepository {

    private final EntityManager em;

    public RouletteRecDto findOne(RouletteRecCriteriaDto request) {

        //TODO -> where g.desc=:request에서 문제가 발생한다!

        TypedQuery<RouletteRecDto> query = em.createQuery( //TODO -> language 컬럼에 저장된 값 중 하나로 고정하기
                "select new opgg.weba.JamPick.dto.RouletteRecDto(i.id, i.headerImage)" +
                        " from IndieApp i join i.genres g where g.description='호러'", RouletteRecDto.class);


        RouletteRecDto result = query.getSingleResult(); //TODO -> json 내에서 객체 안에 담겨 있게 하기 위함인데 바로 리턴해도 객체로 감싸지는지 확인하기

        return result;
    }

}
