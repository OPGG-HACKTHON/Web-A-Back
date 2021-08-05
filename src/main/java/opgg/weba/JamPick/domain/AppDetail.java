package opgg.weba.JamPick.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class AppDetail {

    @Id
    @Column(name = "app_id")
    private Long appId;

    @Column(name = "name")
    private String name;

    @Column(name = "is_free")
    private Boolean isFree;

    @Column(name = "detailed_description")
    private String detailedDescription;

    @Column(name = "release_date")
    private String releaseDate;

    @OneToMany(mappedBy = "appDetail")
    @JoinColumn(name = "genre_id")
    private List<Genre> genres = new ArrayList<>(); //NPE를 방지하기 위한 초기화

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "appDetail") //예상하지 못한 SQL이 발생 방지
    @JoinColumn(name = "indie_app")
    private IndieApp indieApp;

    @OneToMany(mappedBy = "appDetail")
    @JoinColumn(name = "movie")
    private List<Movie> movies = new ArrayList<>();

    @OneToMany(mappedBy = "appDetail")
    @JoinColumn(name = "screenshot")
    private List<Screenshot> screenshots = new ArrayList<>();
}
