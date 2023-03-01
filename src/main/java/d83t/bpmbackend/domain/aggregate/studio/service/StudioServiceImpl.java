package d83t.bpmbackend.domain.aggregate.studio.service;

import d83t.bpmbackend.domain.aggregate.location.entity.Location;
import d83t.bpmbackend.domain.aggregate.location.repository.LocationRepository;
import d83t.bpmbackend.domain.aggregate.studio.dto.StudioRequestDto;
import d83t.bpmbackend.domain.aggregate.studio.dto.StudioResponseDto;
import d83t.bpmbackend.domain.aggregate.studio.entity.Studio;
import d83t.bpmbackend.domain.aggregate.studio.repository.StudioRepository;
import d83t.bpmbackend.exception.CustomException;
import d83t.bpmbackend.exception.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudioServiceImpl implements StudioService {

    private final StudioRepository studioRepository;
    private final LocationRepository locationRepository;

    @Override
    @Transactional
    public StudioResponseDto createStudio(StudioRequestDto requestDto) {
        Location location = locationRepository.findById(requestDto.getLocationId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_LOCATION));
        if (studioRepository.existsStudioByNameAndLocation(requestDto.getName(), location)) {
            throw new CustomException(Error.STUDIO_ALREADY_EXISTS);
        }

        String[] address = location.getAddress().split(" ");

        Studio studio = Studio.builder()
                .name(requestDto.getName())
                .location(location)
                .firstTag(address[0])
                .secondTag(address[1])
                .phone(requestDto.getPhone())
                .sns(requestDto.getSns())
                .openHours(requestDto.getOpenHours())
                .price(requestDto.getPrice())
                .build();
        studioRepository.save(studio);

        return new StudioResponseDto(studio);
    }

    @Override
    public StudioResponseDto findById(Long studioId) {
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_STUDIO));
        return new StudioResponseDto(studio);
    }

    @Override
    public StudioResponseDto searchStudio(String q) {
        Optional<Studio> studio = studioRepository.findByName(q);
        if(studio.isEmpty()){
            throw new CustomException(Error.NOT_FOUND_STUDIO);
        }
        return new StudioResponseDto(studio.get());
    }
}
