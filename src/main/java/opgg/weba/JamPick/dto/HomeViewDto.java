package opgg.weba.JamPick.dto;

import lombok.Data;

import java.util.List;

@Data
public class HomeViewDto {

    private final List<RandomRecDto> randomRecList;
}
