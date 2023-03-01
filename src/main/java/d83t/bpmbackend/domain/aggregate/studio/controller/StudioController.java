package d83t.bpmbackend.domain.aggregate.studio.controller;

import d83t.bpmbackend.domain.aggregate.studio.dto.StudioCreateRequestDto;
import d83t.bpmbackend.domain.aggregate.studio.dto.StudioResponseDto;
import d83t.bpmbackend.domain.aggregate.studio.service.StudioService;
import d83t.bpmbackend.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/studio")
@Slf4j
public class StudioController {

    private final StudioService studioService;

    @Operation(summary = "스튜디오 등록 API", description = "스튜디오 필수, 추가 정보를 받아 등록")
    @ApiResponse(responseCode = "201", description = "스튜디오 등록 성공", content = @Content(schema = @Schema(implementation = StudioResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "스튜디오를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "이미 등록된 스튜디오입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping
    public StudioResponseDto createStudio(@ModelAttribute @Valid StudioCreateRequestDto requestDto) {
        log.info("name : " + requestDto.getName());
        return studioService.createStudio(requestDto);
    }

    @Operation(summary = "스튜디오 조회 API", description = "스튜디오 정보를 조회합니다")
    @ApiResponse(responseCode = "200", description = "스튜디오 조회 성공", content = @Content(schema = @Schema(implementation = StudioResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "스튜디오를 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/{studioId}")
    public StudioResponseDto findStudioById(@PathVariable Long studioId) {
        log.info("id : " + studioId);
        return studioService.findById(studioId);
    }
}
