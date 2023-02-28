package d83t.bpmbackend.domain.aggregate.user.service;

import d83t.bpmbackend.domain.aggregate.studio.entity.Studio;
import d83t.bpmbackend.domain.aggregate.studio.repository.StudioRepository;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleDto;
import d83t.bpmbackend.domain.aggregate.user.dto.ScheduleResponse;
import d83t.bpmbackend.domain.aggregate.user.entity.Schedule;
import d83t.bpmbackend.domain.aggregate.user.repository.ScheduleRepository;
import d83t.bpmbackend.domain.aggregate.user.repository.UserRepository;
import d83t.bpmbackend.security.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    void 스케줄등록하기() {
        ScheduleDto scheduleDto = ScheduleDto.builder()
                .date("2022-01-01")
                .time("17:54:32")
                .memo("메모입니다.")
                .studioName("스튜디오 이롬")
                .build();

        Mockito.when(studioRepository.findByName(Mockito.any(String.class))).thenReturn(
                Optional.of(Studio.builder()
                        .name("스튜디오이름")
                        .build()));

        Mockito.when(scheduleRepository.save(Mockito.any(Schedule.class))).thenReturn(null);

        userService.registerSchedule(scheduleDto);
    }
}