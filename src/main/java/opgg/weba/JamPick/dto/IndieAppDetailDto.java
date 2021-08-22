package opgg.weba.JamPick.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import opgg.weba.JamPick.domain.Genre;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class IndieAppDetailDto {
    private Long id;

    private String name;

    private String shortDescription;

    private String headerImage;

    private String releaseDate;

    private List<String> genres;

    private List<String> screenshots;

    private List<String> movies;
}
