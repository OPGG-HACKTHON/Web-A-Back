package opgg.weba.JamPick.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        this.genres = Stream.of(genres.split(",")).limit(3).collect(Collectors.toList());
    }
}
