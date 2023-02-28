package d83t.bpmbackend.domain.aggregate.location.service;

import d83t.bpmbackend.domain.aggregate.location.dto.LocationRequestDto;
import d83t.bpmbackend.domain.aggregate.location.dto.LocationResponseDto;

public interface LocationService {
    LocationResponseDto createLocation(LocationRequestDto locationRequestDto);
    LocationResponseDto findById(Long locationId);
}
