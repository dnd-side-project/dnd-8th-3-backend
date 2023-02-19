package d83t.bpmbackend.domain.aggregate.user.controller;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileDto;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileResponse;
import d83t.bpmbackend.domain.aggregate.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
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
}
