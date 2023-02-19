package d83t.bpmbackend.domain.aggregate.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import d83t.bpmbackend.domain.aggregate.profile.dto.ProfileResponse;
import d83t.bpmbackend.domain.aggregate.user.service.UserServiceImpl;
import d83t.bpmbackend.exception.CustomException;
import d83t.bpmbackend.exception.Error;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Random;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserServiceImpl userService;

    @Test
    void 카카오_ID_검증_성공() throws Exception {
        Random random = new Random();
        Long kakaoId = random.nextLong();
        ProfileResponse profileResponse = ProfileResponse.builder()
                .nickname("nickname")
                .bio("bio")
                .build();

        Mockito.when(userService.verification(Mockito.eq(kakaoId))).thenReturn(profileResponse);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/"+ kakaoId +"/verification"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.profile.nickname", Matchers.equalTo(profileResponse.getNickname())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.profile.bio", Matchers.equalTo(profileResponse.getBio())));
    }

    @Test
    void 카카오_ID_검증_실패() throws Exception {
        Random random = new Random();
        Long kakaoId = random.nextLong();

        Mockito.when(userService.verification(Mockito.any(Long.class))).thenThrow(new CustomException(Error.NOT_FOUND_USER_ID));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/"+ kakaoId +"/verification"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.message", Matchers.equalTo(Error.NOT_FOUND_USER_ID.getMessage())));
    }
}