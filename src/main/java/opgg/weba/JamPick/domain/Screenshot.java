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

    @Column(name = "path_thumbnail")
    private String pathThumbnail;

    @Column(name = "path_full")
    private String pathFull;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indie_app_id")
    private IndieApp indieApp;
}
