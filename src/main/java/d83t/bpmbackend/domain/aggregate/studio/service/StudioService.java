package d83t.bpmbackend.domain.aggregate.studio.service;

import d83t.bpmbackend.domain.aggregate.studio.dto.StudioCreateRequestDto;
import d83t.bpmbackend.domain.aggregate.studio.dto.StudioDto;

public interface StudioService {
    StudioDto createStudio(StudioCreateRequestDto requestDto);

    StudioDto searchStudio(String q);
}
