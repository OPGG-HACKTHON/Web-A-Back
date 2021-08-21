package opgg.weba.JamPick.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class RouletteRecDto {
    private final Long id;
    private final String name;
    private final String header_image;
    private List<String> genres;

    public RouletteRecDto(BigInteger id, String name, String header_image, String genres) {
        this.id = id.longValue();
        this.name = name;
        this.header_image = header_image;
        this.genres = List.of(genres.split(","));
    }

    public RouletteRecDto(Long id, String name, String header_image, List<String> genres) {
        this.id = id;
        this.name = name;
        this.header_image = header_image;
        this.genres = genres;
    }
}
