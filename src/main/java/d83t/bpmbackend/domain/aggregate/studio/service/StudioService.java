package d83t.bpmbackend.domain.aggregate.studio.service;

import d83t.bpmbackend.domain.aggregate.studio.dto.StudioRequestDto;
import d83t.bpmbackend.domain.aggregate.studio.dto.StudioResponseDto;

public interface StudioService {
    StudioResponseDto searchStudio(String q);
    StudioResponseDto createStudio(StudioRequestDto requestDto);
    StudioResponseDto findById(Long studioId);
}
