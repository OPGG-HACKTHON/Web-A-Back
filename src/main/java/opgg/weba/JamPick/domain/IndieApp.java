package opgg.weba.JamPick.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class IndieApp {

    @Id
    @Column(name = "indie_app_id")
    private Long id;

    @Column(name = "average_forever") //어떤 용도?
    private Integer averageForever;

    @Column(name = "ccu") //어떤 용도?
    private Integer ccu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id")
    private AppDetail appId;

    @OneToMany(mappedBy = "indie_app")
    @JoinColumn(name = "genre_id")
    private List<Genre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "indie_app")
    @JoinColumn(name = "movie_id")
    private List<Movie> movies = new ArrayList<>();

    @OneToMany(mappedBy = "indie_app")
    @JoinColumn(name = "screenshot_id")
    private List<Screenshot> screenshots = new ArrayList<>();
}
