package d83t.bpmbackend.domain.aggregate.community.controller;

import d83t.bpmbackend.domain.aggregate.community.dto.BodyShapeRequest;
import d83t.bpmbackend.domain.aggregate.community.dto.BodyShapeResponse;
import d83t.bpmbackend.domain.aggregate.community.service.BodyShapeService;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleResponse;
import d83t.bpmbackend.domain.aggregate.user.entity.User;
import d83t.bpmbackend.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community/body-shape")
@Slf4j
public class BodyShapeController {

    private final BodyShapeService bodyShapeService;

    @Operation(summary = "내 눈바디 남기기 등록 API", description = "사용자가 내 눈바디를 등록합니다. token을 넘겨야합니다.")
    @ApiResponse(responseCode = "200", description = "내 눈바디 등록 성공", content = @Content(schema = @Schema(implementation = BodyShapeResponse.SingleBodyShape.class)))
    @ApiResponse(responseCode = "400", description = "이미지가 5개 넘게 들어왔습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "잘못된 유저가 들어왔습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping
    public BodyShapeResponse.SingleBodyShape createBoastArticle(
            @AuthenticationPrincipal User user,
            @RequestPart List<MultipartFile> files,
            @ModelAttribute BodyShapeRequest bodyShapeRequest) {
        return BodyShapeResponse.SingleBodyShape.builder().bodyShapeArticle(bodyShapeService.createBoastArticle(user, files, bodyShapeRequest)).build();
    }

    @Operation(summary = "내 눈바디 남기기 리스트 조회 API", description = "사용자가 내 눈바디를 조회합니다. token을 넘겨야합니다.")
    @ApiResponse(responseCode = "200", description = "내 눈바디 등록 성공", content = @Content(schema = @Schema(implementation = BodyShapeResponse.MultiBodyShapes.class)))
    @GetMapping
    public BodyShapeResponse.MultiBodyShapes getBodyShapes(
            @AuthenticationPrincipal User user,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "offset", required = false) Integer offset) {
        List<BodyShapeResponse> bodyShapes = bodyShapeService.getBodyShapes(user, limit, offset);
        return BodyShapeResponse.MultiBodyShapes.builder().bodyShapeArticles(bodyShapes).bodyShapeCount(bodyShapes.size()).build();
    }

    @Operation(summary = "내 눈바디 남기기 상세 조회 API", description = "사용자가 내 눈바디를 상세 조회합니다. token을 넘겨야합니다.")
    @ApiResponse(responseCode = "200", description = "내 눈바디 등록 성공", content = @Content(schema = @Schema(implementation = BodyShapeResponse.SingleBodyShape.class)))
    @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/{bodyShapeId}")
    public BodyShapeResponse.SingleBodyShape getBodyShape(
            @AuthenticationPrincipal User user,
            @PathVariable Long bodyShapeId) {
        return BodyShapeResponse.SingleBodyShape.builder().bodyShapeArticle(bodyShapeService.getBodyShape(user, bodyShapeId)).build();
    }

    @Operation(summary = "내 눈바디 남기기 삭제 API", description = "사용자가 내 눈바디를 삭제. token을 넘겨야합니다.")
    @ApiResponse(responseCode = "200", description = "내 눈바디 삭제 성공")
    @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @DeleteMapping("/{bodyShapeId}")
    public void deleteBodyShape(
            @AuthenticationPrincipal User user,
            @PathVariable Long bodyShapeId) {
        bodyShapeService.deleteBodyShape(user, bodyShapeId);
    }

}
