package d83t.bpmbackend.domain.aggregate.location.service;

import d83t.bpmbackend.domain.aggregate.location.dto.LocationRequestDto;
import d83t.bpmbackend.domain.aggregate.location.dto.LocationResponseDto;
import d83t.bpmbackend.domain.aggregate.location.entity.Location;
import d83t.bpmbackend.domain.aggregate.location.repository.LocationRepository;
import d83t.bpmbackend.exception.CustomException;
import d83t.bpmbackend.exception.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    @Transactional
    public LocationResponseDto createLocation(LocationRequestDto locationRequestDto) {
        if (locationRepository.existsLocationByLatitudeAndLongitude(locationRequestDto.getLatitude(), locationRequestDto.getLongitude())) {
            throw new CustomException(Error.LOCATION_ALREADY_EXISTS);
        }
        Location location = locationRepository.save(locationRequestDto.toEntity());
        return new LocationResponseDto(location);
    }

    @Override
    public LocationResponseDto findById(Long locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_LOCATION));
        return new LocationResponseDto(location);
    }
}
