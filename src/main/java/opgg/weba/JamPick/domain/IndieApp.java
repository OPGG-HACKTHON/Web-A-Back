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

    @Column(name = "average_forever")
    private Integer averageForever;

    @Column(name = "ccu")
    private Integer ccu;

    @Column(name = "header_image")
    private String headerImage;

    @OneToMany(mappedBy = "indieApp")
    private List<IndieAppDetail> indieAppDetails = new ArrayList<>();

    @OneToMany(mappedBy = "indieApp")
    private List<Genre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "indieApp")
    private List<Movie> movies = new ArrayList<>();

    @OneToMany(mappedBy = "indieApp")
    private List<Screenshot> screenshots = new ArrayList<>();
}
