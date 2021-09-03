package opgg.weba.JamPick.controller;

import opgg.weba.JamPick.common.ResponseCode;
import opgg.weba.JamPick.dto.IndieAppDetailDto;
import opgg.weba.JamPick.dto.ResponseDto;
import opgg.weba.JamPick.service.IndieAppDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IndieAppDetailController {

    @Autowired
    private IndieAppDetailService indieAppDetailService;

    @GetMapping(value = "/detail/{indieAppId}")
    public ResponseEntity<ResponseDto> getIndieAppDetail(@PathVariable("indieAppId") Long indieAppId) {
        try {
            IndieAppDetailDto indieAppDetailDto = indieAppDetailService.getIndieAppDetail(indieAppId);

            ResponseDto responseDto = ResponseDto.builder()
                    .code(ResponseCode.SUCCESS.getValue())
                    .responseMessage("상세 조회 성공")
                    .data(indieAppDetailDto)
                    .build();

            return ResponseEntity.ok(responseDto);
        } catch (RuntimeException e) {
            ResponseDto responseDto = ResponseDto.builder()
                    .code(ResponseCode.NOT_FOUND.getValue())
                    .responseMessage(e.getMessage())
                    .data(null)
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }
}