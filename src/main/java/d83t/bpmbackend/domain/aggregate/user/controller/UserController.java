package d83t.bpmbackend.domain.aggregate.user.controller;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileDto;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileResponse;
import d83t.bpmbackend.domain.aggregate.user.service.UserService;
import d83t.bpmbackend.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "카카오 로그인 API", description = "카카오 uid, profile에 대한 정보를 받아 프로필을 생성하고 로그인을 진행합니다.")
    @PostMapping(path = "/signup")
    public ProfileResponse signUp(
            @RequestParam Long kakaoId,
            @ModelAttribute ProfileRequest profileRequest,
            @RequestParam MultipartFile file) {
        return userService.signUp(kakaoId, profileRequest, file);
    }

    @Operation(summary = "카카오 uuid 체크 API", description = "카카오 uid을 받아 이미 있는 유저인지 판단하는 API입니다.")
    @ApiResponse(responseCode = "200", description = "카카오 uuid가 조회되었습니다.", content = @Content(schema = @Schema(implementation = ProfileResponse.class)))
    @ApiResponse(responseCode = "404", description = "카카오 uuid를 찾지 못하였습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping(path = "/{kakaoId}/verification")
    public ProfileResponse verification(@PathVariable Long kakaoId) {
        return userService.verification(kakaoId);
    }
}
