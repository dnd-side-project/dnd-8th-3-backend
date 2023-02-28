package d83t.bpmbackend.domain.aggregate.community.controller;

import d83t.bpmbackend.domain.aggregate.community.dto.BodyShapeRequest;
import d83t.bpmbackend.domain.aggregate.community.dto.BodyShapeResponse;
import d83t.bpmbackend.domain.aggregate.community.service.BodyShapeService;
import d83t.bpmbackend.domain.aggregate.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community/body-shape")
@Slf4j
public class BodyShapeController {

    private final BodyShapeService bodyShapeService;

    @PostMapping
    public BodyShapeResponse.SingleBodyShape createBoastArticle(
            @AuthenticationPrincipal User user,
            @RequestPart List<MultipartFile> files,
            @ModelAttribute BodyShapeRequest bodyShapeRequest) {
        return BodyShapeResponse.SingleBodyShape.builder().bodyShapeArticle(bodyShapeService.createBoastArticle(user, files, bodyShapeRequest)).build();
    }

    @GetMapping
    public BodyShapeResponse.MultiBodyShapes getBodyShapes(
            @AuthenticationPrincipal User user,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "offset", required = false) Integer offset) {
        List<BodyShapeResponse> bodyShapes = bodyShapeService.getBodyShapes(user, limit, offset);
        return BodyShapeResponse.MultiBodyShapes.builder().bodyShapeArticles(bodyShapes).bodyShapeCount(bodyShapes.size()).build();
    }
}
