package or.sopt.assignment.domain.post.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.post.service.PostLikesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts/likes")
@RequiredArgsConstructor
@Tag(name = "게시글 좋아요 관련 API")
public class PostLikesController {

    private final PostLikesService postLikesService;
}
