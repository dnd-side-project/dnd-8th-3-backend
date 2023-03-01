package d83t.bpmbackend.domain.aggregate.studio.service;

import d83t.bpmbackend.domain.aggregate.studio.dto.StudioCreateRequestDto;
import d83t.bpmbackend.domain.aggregate.studio.dto.StudioResponseDto;

public interface StudioService {
    StudioResponseDto createStudio(StudioCreateRequestDto requestDto);
    StudioResponseDto findById(Long studioId);
}
