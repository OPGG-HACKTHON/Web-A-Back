package opgg.weba.JamPick.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Movie {

    @Id
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "name")
    private Long name;

    @Column(name = "mp4")
    private String mp4;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id")
    private AppDetail appId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indie_app")
    private IndieApp indieAppId;
}
