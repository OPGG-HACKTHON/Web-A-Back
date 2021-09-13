package opgg.weba.JamPick.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class IndieChipPickDto {

    private final Long indie_app_id;
    private final String name;
    private final Integer like_count;
    private final List<String> movies;
    private final List<String> genres;
}
