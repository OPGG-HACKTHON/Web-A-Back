package opgg.weba.JamPick.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Screenshot {

    @Id
    @Column(name = "screenshot_id")
    private Long screenshotId;

    @Column(name = "path_thumb_nail")
    private String pathThumbNail;

    @Column(name = "path_full")
    private String pathFull;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indie_app_id")
    private IndieApp indieApp;
}
