package d83t.bpmbackend.domain.aggregate.studio.service;

import d83t.bpmbackend.domain.aggregate.location.entity.Location;
import d83t.bpmbackend.domain.aggregate.location.repository.LocationRepository;
import d83t.bpmbackend.domain.aggregate.studio.dto.StudioCreateRequestDto;
import d83t.bpmbackend.domain.aggregate.studio.dto.StudioDto;
import d83t.bpmbackend.domain.aggregate.studio.entity.Studio;
import d83t.bpmbackend.domain.aggregate.studio.repository.StudioRepository;
import d83t.bpmbackend.exception.CustomException;
import d83t.bpmbackend.exception.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudioServiceImpl implements StudioService {

    private final StudioRepository studioRepository;
    private final LocationRepository locationRepository;

    @Transactional
    public StudioDto createStudio(StudioCreateRequestDto requestDto) {
        Location location = locationRepository.findById(requestDto.getLocationId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_STUDIO));
        if (studioRepository.existsStudioByNameAndLocation(requestDto.getName(), location)) {
            throw new CustomException(Error.STUDIO_ALREADY_EXISTS);
        }

        Studio studio = Studio.builder()
                .name(requestDto.getName())
                .location(location)
                .phone(requestDto.getPhone())
                .sns(requestDto.getSns())
                .openHours(requestDto.getOpenHours())
                .price(requestDto.getPrice())
                .build();

        return new StudioDto(studio);
    }
}