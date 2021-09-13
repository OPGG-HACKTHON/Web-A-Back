package opgg.weba.JamPick.repository;

import opgg.weba.JamPick.domain.IndieChipPickAppDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndieChipPickDetailRepository extends JpaRepository<IndieChipPickAppDetail, Long> {

    @Query("select icpd.indieApp.id from IndieChipPickAppDetail icpd where icpd.indieChipPickAppDetailId = :id")
    Long findIndieAppIdById(@Param("id") Long indieChipPickAppDetailId);

    @Query("select icpd.likeCount from IndieChipPickAppDetail icpd where icpd.indieChipPickAppDetailId = :id")
    Integer findLikeCountById(@Param("id") Long indieChipPickAppDetailId);

    @Query("select icpd.indieChipPickAppDetailId from IndieChipPickAppDetail icpd join icpd.indieChipPick AS icp where icp.isVoteEnd = false")
    List<Long> findOngoingIndieChipPickAppDetailIds();
}
