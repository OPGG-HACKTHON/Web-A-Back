package opgg.weba.JamPick.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class RouletteRecCriteriaDto {
    private List<String> genreList = new ArrayList<>(); //TODO: 여기에 requestbody에 있는 장르들이 다 제대로 들어가는지 확인 필요!
}
