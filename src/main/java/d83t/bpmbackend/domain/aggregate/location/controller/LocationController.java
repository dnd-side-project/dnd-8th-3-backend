package d83t.bpmbackend.domain.aggregate.location.controller;

import d83t.bpmbackend.domain.aggregate.location.dto.LocationRequestDto;
import d83t.bpmbackend.domain.aggregate.location.dto.LocationResponseDto;
import d83t.bpmbackend.domain.aggregate.location.service.LocationService;
import d83t.bpmbackend.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/location")
@Slf4j
public class LocationController {

    private final LocationService locationService;

    @Operation(summary = "위치 등록 API", description = "스튜디오 등록 전 업체 위치를 받아 저장")
    @ApiResponse(responseCode = "201", description = "위치 등록 성공", content = @Content(schema = @Schema(implementation = LocationResponseDto.class)))
    @ApiResponse(responseCode = "409", description = "이미 등록된 위치 정보입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping
    public LocationResponseDto createLocation(@ModelAttribute @Valid LocationRequestDto locationRequestDto) {
        log.info("address: " + locationRequestDto.getAddress());
        return locationService.createLocation(locationRequestDto);
    }
}
