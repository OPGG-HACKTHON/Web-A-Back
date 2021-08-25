package opgg.weba.JamPick.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Vote {

    @Id
    @Column(name = "vote_id")
    private Long voteId;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "is_vote_end")
    private Boolean isVoteEnd;

    @OneToMany(mappedBy = "vote")
    private List<VoteAppDetail> voteAppDetails = new ArrayList<>();
}
