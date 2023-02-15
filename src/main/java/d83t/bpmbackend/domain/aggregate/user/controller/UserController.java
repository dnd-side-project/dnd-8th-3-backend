package d83t.bpmbackend.domain.aggregate.user.controller;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileDto;
import d83t.bpmbackend.domain.aggregate.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/signup")
    public ProfileDto.Single signUp(
            @RequestParam Long kakaoId,
            @ModelAttribute ProfileDto profileDto,
            @RequestParam MultipartFile file) {
        return new ProfileDto.Single(userService.signUp(kakaoId, profileDto, file));
    }
}
