package d83t.bpmbackend.domain.aggregate.user.service;

import d83t.bpmbackend.domain.aggregate.studio.entity.Studio;
import d83t.bpmbackend.domain.aggregate.studio.repository.StudioRepository;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleRequest;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleResponse;
import d83t.bpmbackend.domain.aggregate.user.entity.Schedule;
import d83t.bpmbackend.domain.aggregate.user.entity.User;
import d83t.bpmbackend.domain.aggregate.user.repository.ScheduleRepository;
import d83t.bpmbackend.security.jwt.JwtService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private StudioRepository studioRepository;

    @Mock
    private JwtService jwtService;

    @Test
    void 스케줄조회하기() {

        User user = User.builder()
                .id(99877L)
                .build();

        Mockito.when(scheduleRepository.findByUserId(Mockito.eq(user.getId()))).thenReturn(
                Optional.of(Schedule.builder()
                        .studio(Studio.builder()
                                .name("스튜디오이름")
                                .build())
                        .memo("메모")
                        .build()));

        ScheduleResponse schedule = userService.getSchedule(user);
        Assertions.assertThat(schedule.getMemo()).isEqualTo("메모");
        Assertions.assertThat(schedule.getStudioName()).isEqualTo("스튜디오이름");
    }

    @Test
    void 스케줄등록하기() {
        ScheduleRequest scheduleRequest = ScheduleRequest.builder()
                .date("2022-01-01")
                .time("17:54:32")
                .memo("메모입니다.")
                .studioName("스튜디오 이롬")
                .build();

        User user = User.builder()
                .build();

        Mockito.when(studioRepository.findByName(Mockito.any(String.class))).thenReturn(
                Optional.of(Studio.builder()
                        .name("스튜디오이름")
                        .build()));

        Mockito.when(scheduleRepository.save(Mockito.any(Schedule.class))).thenReturn(null);

        userService.registerSchedule(user, scheduleRequest);
    }
}