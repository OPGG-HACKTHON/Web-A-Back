package opgg.weba.JamPick.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Screenshot {

    @Id
    @Column(name = "screenshot_id") //db 컬럼명에 오타 존재!
    private Long screenshotId;

    @Column(name = "path_thumb_nail")
    private String pathThumbNail;

    @Column(name = "path_full")
    private String pathFull;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id")
    private AppDetail appId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indie_app")
    private IndieApp indieAppId;
}
