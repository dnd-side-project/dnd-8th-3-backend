package d83t.bpmbackend.domain.aggregate.user.controller;

import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileRequest;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileResponse;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleDto;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleResponse;
import d83t.bpmbackend.domain.aggregate.user.entity.User;
import d83t.bpmbackend.domain.aggregate.user.dto.UserRequestDto;
import d83t.bpmbackend.domain.aggregate.user.service.UserService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @Operation(summary = "카카오 로그인 API", description = "카카오 uid, profile에 대한 정보를 받아 프로필을 생성하고 로그인을 진행합니다.")
    @ApiResponse(responseCode = "200", description = "신규 회원 등록 성공", content = @Content(schema = @Schema(implementation = ProfileResponse.class)))
    @ApiResponse(responseCode = "409", description = "이미 존재하는 유저입니다. 닉네임이나 카카오 uid확인 필요", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping(path = "/signup")
    public ProfileResponse signUp(
            @ModelAttribute ProfileRequest profileRequest,
            @RequestParam MultipartFile file) {
        log.info(profileRequest.getKakaoId() +" " + profileRequest.getNickname() + " " +  profileRequest.getBio() + " " +file.getOriginalFilename());
        return userService.signUp(profileRequest, file);
    }

    @Operation(summary = "카카오 uuid 체크 API", description = "카카오 uid을 받아 이미 있는 유저인지 판단하는 API입니다.")
    @ApiResponse(responseCode = "200", description = "카카오 uuid가 조회되었습니다.", content = @Content(schema = @Schema(implementation = ProfileResponse.class)))
    @ApiResponse(responseCode = "404", description = "카카오 uuid를 찾지 못하였습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping(path = "/verification")
    public ProfileResponse verification(@RequestBody UserRequestDto userRequestDto) {
        log.info("id:" + userRequestDto.getKakaoId());
        return userService.verification(userRequestDto);
    }

    @Operation(summary = "내 일정 조회 API", description = "사용자가 일정을 등록했는지 확인합니다. token을 넘겨야합니다.")
    @ApiResponse(responseCode = "200", description = "내 일정 조회 성공", content = @Content(schema = @Schema(implementation = ScheduleResponse.class)))
    @ApiResponse(responseCode = "404", description = "등록된 일정이 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/schedule")
    public ScheduleResponse getSchedule(@AuthenticationPrincipal User user){
        log.info("request user : " + user.getKakaoId());
        return userService.getSchedule(user);
    }


    @Operation(summary = "내 일정 등록 API", description = "사용자가 일정을 등록합니다. token을 넘겨야합니다.")
    @ApiResponse(responseCode = "200", description = "내 일정 등록 성공", content = @Content(schema = @Schema(implementation = ScheduleResponse.class)))
    @ApiResponse(responseCode = "404", description = "스튜디오 이름이 잘못 들어왔습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping("/schedule")
    public ScheduleResponse registerSchedule(@AuthenticationPrincipal User user, @RequestBody ScheduleDto scheduleDto){
        log.info("request : "+ scheduleDto.toString());
        return userService.registerSchedule(user, scheduleDto);
    }
}
