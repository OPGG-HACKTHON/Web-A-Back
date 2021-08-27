package opgg.weba.JamPick.repository;

import lombok.RequiredArgsConstructor;
import opgg.weba.JamPick.domain.IndieApp;
import opgg.weba.JamPick.domain.Vote;
import opgg.weba.JamPick.domain.VoteAppDetail;
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

    private void endOngoingVote() { //1. 진행중이었던 칩픽 종료하고
        em.createQuery("update Vote v set v.isVoteEnd = true where v.isVoteEnd = false");
    }

    private void createNewVote() {

        Date time = new Date();

        Vote new_vote = new Vote();
        new_vote.setCreatedAt(time);
        new_vote.setIsVoteEnd(false);

        String sql = "SELECT i FROM indie_app AS i ORDER BY rand()";
        List<IndieApp> indieApps = em.createNativeQuery(sql).setMaxResults(2).getResultList();

        for (int i = 0; i < 2; i++) {
            VoteAppDetail voteAppDetail = new VoteAppDetail();
            voteAppDetail.setLikeCount(0);
            voteAppDetail.setVote(new_vote);
            voteAppDetail.setIndieApp(indieApps.get(i));
        }
    }






}
