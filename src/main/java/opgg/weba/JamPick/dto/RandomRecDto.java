package opgg.weba.JamPick.dto;

import lombok.Data;
import opgg.weba.JamPick.domain.Genre;

import java.util.List;

@Data
public class RandomRecDto {
    private final Long id;
    private final String name;
    private final Boolean isFree;
    private final String genres; //TODO -> 배열로 받게 변경
}
