package d83t.bpmbackend.domain.aggregate.studio.service;

import d83t.bpmbackend.domain.aggregate.studio.dto.ReviewRequestDto;
import d83t.bpmbackend.domain.aggregate.studio.dto.ReviewResponseDto;
import d83t.bpmbackend.domain.aggregate.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {
    ReviewResponseDto createReview(Long studioId, User user, List<MultipartFile> files, ReviewRequestDto requestDto);
}
