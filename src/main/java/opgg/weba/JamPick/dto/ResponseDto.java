package opgg.weba.JamPick.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class ResponseDto {
    private final int code;
    private final String responseMessage;
    private final Object data;
}