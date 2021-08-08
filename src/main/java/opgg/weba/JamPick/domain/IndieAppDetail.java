package opgg.weba.JamPick.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class IndieAppDetail {

    @Id
    @Column(name = "indie_app_detail_id")
    private Long id;

    @Column(name = "supported_language") //TODO 데이터 타입 정하기
    private String supportedLanguage;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "language")
    private String language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indie_app_id")
    private IndieApp indieApp;
}