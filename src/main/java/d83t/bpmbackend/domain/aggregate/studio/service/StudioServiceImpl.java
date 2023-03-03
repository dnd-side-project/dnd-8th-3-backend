package d83t.bpmbackend.domain.aggregate.studio.service;

import d83t.bpmbackend.domain.aggregate.studio.dto.StudioRequestDto;
import d83t.bpmbackend.domain.aggregate.studio.dto.StudioResponseDto;
import d83t.bpmbackend.domain.aggregate.studio.entity.Studio;
import d83t.bpmbackend.domain.aggregate.studio.repository.StudioRepository;
import d83t.bpmbackend.exception.CustomException;
import d83t.bpmbackend.exception.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudioServiceImpl implements StudioService {

    private final StudioRepository studioRepository;

    @Override
    @Transactional
    public StudioResponseDto createStudio(StudioRequestDto requestDto) {
        String[] address = requestDto.getAddress().split(" ");

        Studio studio = Studio.builder()
                .name(requestDto.getName())
                .address(requestDto.getAddress())
                .latitude(requestDto.getLatitude())
                .longitude(requestDto.getLongitude())
                .firstTag(address[0])
                .secondTag(address[1])
                .phone(requestDto.getPhone())
                .sns(requestDto.getSns())
                .openHours(requestDto.getOpenHours())
                .price(requestDto.getPrice())
                .build();

        studio.addRecommend(requestDto.getRecommends());
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
    public List<StudioResponseDto> findStudioAll(Integer limit, Integer offset) {
        int pageSize = limit == null ? 20 : limit;
        int pageNumber = offset == null ? 0 : offset;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Studio> studios = studioRepository.findByAll(pageable);

        return studios.stream().map(studio -> {
            return new StudioResponseDto(studio);
        }).collect(Collectors.toList());
    }

    @Override
    public List<StudioResponseDto> searchStudio(String q) {
        List<Studio> studios = studioRepository.searchStudioNames(q);
        if (studios.isEmpty()) {
            throw new CustomException(Error.NOT_FOUND_STUDIO);
        }
        return studios.stream().map(studio -> {
            return new StudioResponseDto(studio);
        }).collect(Collectors.toList());
    }
}
