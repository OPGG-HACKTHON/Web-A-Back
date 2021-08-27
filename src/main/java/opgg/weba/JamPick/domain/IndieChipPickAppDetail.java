package opgg.weba.JamPick.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class IndieChipPickAppDetail {

    @Id @GeneratedValue
    @Column(name = "indie_chip_pick_app_detail_id")
    private Long indieChipPickAppDetailId;

    @Column(name = "like_count")
    private Integer likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indie_chip_pick_id")
    private IndieChipPick indieChipPick;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indie_app_id")
    private IndieApp indieApp;
}
