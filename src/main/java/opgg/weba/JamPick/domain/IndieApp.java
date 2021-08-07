package opgg.weba.JamPick.domain;

import lombok.Getter;
import lombok.Setter;
import opgg.weba.JamPick.domain.Genre;
import opgg.weba.JamPick.domain.Movie;
import opgg.weba.JamPick.domain.Screenshot;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class IndieApp {

    @Id
    @Column(name = "indie_app_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_free")
    private Boolean isFree;

    @Column(name = "detailed_description")
    private String detailedDescription;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "average_forever")
    private Integer averageForever;

    @Column(name = "ccu")
    private Integer ccu;

    @Column(name = "header_image")
    private String header_image;

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
