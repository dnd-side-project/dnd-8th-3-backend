package d83t.bpmbackend.domain.aggregate.studio.service;

import d83t.bpmbackend.domain.aggregate.profile.entity.Profile;
import d83t.bpmbackend.domain.aggregate.profile.repository.ProfileRepository;
import d83t.bpmbackend.domain.aggregate.studio.dto.ReviewRequestDto;
import d83t.bpmbackend.domain.aggregate.studio.dto.ReviewResponseDto;
import d83t.bpmbackend.domain.aggregate.studio.entity.Review;
import d83t.bpmbackend.domain.aggregate.studio.entity.ReviewImage;
import d83t.bpmbackend.domain.aggregate.studio.entity.Studio;
import d83t.bpmbackend.domain.aggregate.studio.repository.ReviewRepository;
import d83t.bpmbackend.domain.aggregate.studio.repository.StudioRepository;
import d83t.bpmbackend.domain.aggregate.user.entity.User;
import d83t.bpmbackend.domain.aggregate.user.repository.UserRepository;
import d83t.bpmbackend.exception.CustomException;
import d83t.bpmbackend.exception.Error;
import d83t.bpmbackend.s3.S3UploaderService;
import d83t.bpmbackend.utils.FileUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final StudioRepository studioRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final S3UploaderService uploaderService;

    @Value("reviews/")
    private String reviewPath;
    @Value("local")
    private String env;

    private String fileDir;

    @PostConstruct
    private void init() {
        if (env.equals("local")) {
            this.fileDir = FileUtils.getUploadPath();
        } else if (env.equals("prod")) {
            this.fileDir = this.reviewPath;
        }
    }

    @Override
    @Transactional
    public ReviewResponseDto createReview(Long studioId, User user, List<MultipartFile> files, ReviewRequestDto requestDto) {
        if (files.size() > 5) {
            throw new CustomException(Error.FILE_SIZE_MAX);
        }

        List<String> filePaths = new ArrayList<>();
        User findUser = userRepository.findByKakaoId(user.getKakaoId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_USER_ID));
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_STUDIO));
        Profile profile = findUser.getProfile();

        Review review = Review.builder()
                .studio(studio)
                .author(profile)
                .rating(requestDto.getRating())
                .content(requestDto.getContent())
                .build();

        for (MultipartFile file : files) {
            String newName = FileUtils.createNewFileName(file.getOriginalFilename());
            String filePath = fileDir + newName;
            review.addReviewImage(ReviewImage.builder()
                    .originFileName(newName)
                    .storagePathName(filePath)
                    .review(review)
                    .build());
            filePaths.add(filePath);

            if (env.equals("prod")) {
                uploaderService.putS3(file, reviewPath, newName);
            } else if (env.equals("local")) {
                try {
                    File localFile = new File(filePath);
                    file.transferTo(localFile);
                    FileUtils.removeNewFile(localFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        studio.addReview(review);
        studioRepository.save(studio);

        return new ReviewResponseDto(review);
    }

    @Override
    public List<ReviewResponseDto> findAll(Long studioId, int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sort));
        Page<Review> reviews = reviewRepository.findByStudioId(studioId, pageable);

        return reviews.stream().map(review -> new ReviewResponseDto(review))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponseDto findById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_REVIEW));
        return new ReviewResponseDto(review);
    }

    // TODO: 작성자인지 판단하는 검증 로직 추가
    @Override
    @Transactional
    public void deleteReview(User user, Long studioId, Long reviewId) {
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_STUDIO));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND_REVIEW));

        studio.removeReview(review);
        reviewRepository.delete(review);
        studioRepository.save(studio);
    }
}
