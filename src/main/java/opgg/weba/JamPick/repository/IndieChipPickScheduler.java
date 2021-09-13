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
public class IndieChipPickScheduler {

    private final EntityManager em;

    @Scheduled(cron = "0 0 12 * * 2")
    public void changeOngoingVote() {
        endOngoingVote();
        createNewVote();
    }

    private void endOngoingVote() {
        em.createQuery("update IndieChipPick v set v.isVoteEnd = true where v.isVoteEnd = false");
    }

    public void createNewVote() {
        Date time = new Date();

        IndieChipPick newIndieChipPick = new IndieChipPick();
        newIndieChipPick.setCreatedAt(time);
        newIndieChipPick.setIsVoteEnd(false);
        em.persist(newIndieChipPick);

        String sql = "SELECT * FROM indie_app ORDER BY rand()";
        List<IndieApp> indieAppList = em.createNativeQuery(sql).setMaxResults(2).getResultList();

        System.out.println("indieAppList = " + indieAppList.get(0).getName());

        for (int i = 0; i < 2; i++) {
            IndieChipPickAppDetail indieChipPickAppDetail = new IndieChipPickAppDetail();
            indieChipPickAppDetail.setLikeCount(0);
            indieChipPickAppDetail.setIndieChipPick(newIndieChipPick);
            indieChipPickAppDetail.setIndieApp(indieAppList.get(0));
            em.persist(indieChipPickAppDetail);
        }
    }
}
