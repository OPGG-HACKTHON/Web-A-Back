package opgg.weba.JamPick.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class RandomRecDto {
    private final Long id;
    private final String name;
    private final Boolean is_free;
    private final String header_image;
    private List<String> genres;


    public RandomRecDto(BigInteger id, String name, Boolean is_free, String header_image, String genres) {
        this.id = id.longValue();
        this.name = name;
        this.is_free = is_free;
        this.header_image = header_image;
        this.genres = Stream.of(genres.split(",")).limit(2).collect(Collectors.toList());
    }
}
