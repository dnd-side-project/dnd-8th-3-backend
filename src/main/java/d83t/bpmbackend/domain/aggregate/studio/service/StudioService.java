package d83t.bpmbackend.domain.aggregate.studio.service;

import d83t.bpmbackend.domain.aggregate.studio.dto.StudioRequestDto;
import d83t.bpmbackend.domain.aggregate.studio.dto.StudioResponseDto;

import java.util.List;

public interface StudioService {
    List<StudioResponseDto> searchStudio(String q);
    StudioResponseDto createStudio(StudioRequestDto requestDto);
    StudioResponseDto findById(Long studioId);

    List<StudioResponseDto> findStudioAll(Integer limit, Integer offset);
}
