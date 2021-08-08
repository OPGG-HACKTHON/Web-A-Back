package opgg.weba.JamPick.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class ResponseDTO {
    private final int status;
    private final String responseMessage;
    private final Object data;
}