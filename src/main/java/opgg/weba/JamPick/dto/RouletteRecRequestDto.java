package opgg.weba.JamPick.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class RouletteRecRequestDto {
    private final List<String> genres = new ArrayList<>();
}
