package opgg.weba.JamPick.repository;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.domain.IndieChipPick;
import opgg.weba.JamPick.domain.IndieChipPickAppDetail;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class IndieChipPickRepository {

    private final EntityManager em;

    @Scheduled(cron = "0 0 12 * * 2")
    public void changeOngoingVote() {

        endOngoingVote();
        createNewVote();

    }

    private void endOngoingVote() {
        em.createQuery("update IndieChipPick v set v.isVoteEnd = true where v.isVoteEnd = false");
    }

    private void createNewVote() {

        Date time = new Date();

        IndieChipPick new_indieChipPick = new IndieChipPick();
        new_indieChipPick.setCreatedAt(time);
        new_indieChipPick.setIsVoteEnd(false);

        String sql = "SELECT i FROM indie_app AS i ORDER BY rand()";
        List<IndieApp> indieApps = em.createNativeQuery(sql).setMaxResults(2).getResultList();

        for (int i = 0; i < 2; i++) {
            IndieChipPickAppDetail indieChipPickAppDetail = new IndieChipPickAppDetail();
            indieChipPickAppDetail.setLikeCount(0);
            indieChipPickAppDetail.setIndieChipPick(new_indieChipPick);
            indieChipPickAppDetail.setIndieApp(indieApps.get(i));
        }
    }

//    public List<IndieChipPickDto> findOngoingIndieChipPick() {
//
//        Locale locale = GetLocale.getLocale();
//
//        JpaResultMapper jpaResultMapper = new JpaResultMapper();
//        String sql = "SELECT vd.indie_chip_pick_app_detail_id, i.name, i.header_image, vd.like_count, group_concat(g.description separator ',')" +
//                " v FROM indie_chip_pick AS v" +
//                " JOIN indie_chip_pick_app_detail AS vd ON v.indie_chip_pick_id = vd.indie_chip_pick_id" +
//                " JOIN indie_app AS i ON vd.indie_app_id = i.indie_app_id" +
//                " JOIN genre AS g ON i.indie_app_id = g.indie_app_id" +
//                " WHERE v.is_vote_end = false AND " +
//                " WHERE g.language = :locale AND" +
//                " i.indie_app_id IN (SELECT i.indie_app_id FROM genre WHERE description IN (:request) group by i.indie_app_id)";
//
//        Query nativeQuery = em.createNativeQuery(sql)
//                .setParameter("locale", locale)
//                .setMaxResults(2);
//
//        List<IndieChipPickDto> result = jpaResultMapper.list(nativeQuery, IndieChipPickDto.class);
//
//        return result;
//    }




}
