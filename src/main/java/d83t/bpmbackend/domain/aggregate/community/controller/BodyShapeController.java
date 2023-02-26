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
    public BodyShapeResponse createBoastArticle(
            @AuthenticationPrincipal User user,
            @RequestPart List<MultipartFile> files,
            @ModelAttribute BodyShapeRequest bodyShapeRequest) {
        log.info(files.toString());
        return bodyShapeService.createBoastArticle(user, files, bodyShapeRequest);
    }
}
