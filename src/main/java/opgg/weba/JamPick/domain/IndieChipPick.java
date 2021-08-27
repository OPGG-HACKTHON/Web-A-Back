package opgg.weba.JamPick.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class IndieChipPick {

    @Id @GeneratedValue
    @Column(name = "indie_chip_pick_id")
    private Long indieChipPickId;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "is_vote_end")
    private Boolean isVoteEnd;

    @OneToMany(mappedBy = "indieChipPick")
    private List<IndieChipPickAppDetail> indieChipPickAppDetails = new ArrayList<>();
}
