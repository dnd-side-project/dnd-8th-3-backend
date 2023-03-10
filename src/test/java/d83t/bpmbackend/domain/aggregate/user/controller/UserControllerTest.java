package d83t.bpmbackend.domain.aggregate.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import d83t.bpmbackend.config.WithAuthUser;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileResponse;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleRequest;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleResponse;
import d83t.bpmbackend.domain.aggregate.user.dto.UserRequestDto;
import d83t.bpmbackend.domain.aggregate.user.entity.User;
import d83t.bpmbackend.domain.aggregate.user.service.UserServiceImpl;
import d83t.bpmbackend.exception.CustomException;
import d83t.bpmbackend.exception.Error;
import d83t.bpmbackend.security.jwt.JwtService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserServiceImpl userService;

    @MockBean
    JwtService jwtService;


    @Test
    void 카카오_ID_검증_성공() throws Exception {
        Random random = new Random();
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .kakaoId(random.nextLong())
                .build();
        ProfileResponse profileResponse = ProfileResponse.builder()
                .nickname("nickname")
                .bio("bio")
                .build();

        Mockito.when(userService.verification(Mockito.any(userRequestDto.getClass()))).thenReturn(profileResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/verification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nickname", Matchers.equalTo(profileResponse.getNickname())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bio", Matchers.equalTo(profileResponse.getBio())));
    }

    @Test
    void 카카오_ID_검증_실패() throws Exception {
        Random random = new Random();
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .kakaoId(random.nextLong())
                .build();

        Mockito.when(userService.verification(Mockito.any(userRequestDto.getClass()))).thenThrow(new CustomException(Error.NOT_FOUND_USER_ID));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/verification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.equalTo(Error.NOT_FOUND_USER_ID.getMessage())));
    }

    @Test
    @WithAuthUser
    void 스케줄_조회_테스트() throws Exception{
        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .date("2022-01-01")
                .time("17:54:32")
                .memo("메모입니다.")
                .studioName("스튜디오 이롬")
                .build();

        ScheduleResponse scheduleResponse = ScheduleResponse.builder()
                .studioName(scheduleRequest.getStudioName())
                .time(convertTimeFormat(scheduleRequest.getTime()))
                .date(convertDateFormat(scheduleRequest.getDate()))
                .memo(scheduleRequest.getMemo())
                .build();

        Mockito.when(userService.getSchedule(Mockito.any(User.class))).thenReturn(scheduleResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/schedule"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithAuthUser
    void 스케줄_등록_입력_테스트() throws Exception {
        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .date("2022-01-01")
                .time("17:54:32")
                .memo("메모입니다.")
                .studioName("스튜디오 이롬")
                .build();

        ScheduleResponse scheduleResponse = ScheduleResponse.builder()
                .studioName(scheduleRequest.getStudioName())
                .time(convertTimeFormat(scheduleRequest.getTime()))
                .date(convertDateFormat(scheduleRequest.getDate()))
                .memo(scheduleRequest.getMemo())
                .build();

        Mockito.when(userService.registerSchedule(Mockito.any(User.class),Mockito.any(ScheduleRequest.class))).thenReturn(scheduleResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(scheduleRequest))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private LocalDate convertDateFormat(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dateTimeFormatter);
    }

    private LocalTime convertTimeFormat(String time) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.parse(time, dateTimeFormatter);
    }
}