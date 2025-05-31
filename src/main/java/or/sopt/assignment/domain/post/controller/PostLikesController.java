package or.sopt.assignment.domain.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.post.service.PostLikesService;
import or.sopt.assignment.global.api.exception.status.SuccessStatus;
import or.sopt.assignment.global.api.reponse.ApiResponseUtil;
import or.sopt.assignment.global.api.reponse.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts/likes")
@RequiredArgsConstructor
@Tag(name = "게시글 좋아요 관련 API")
public class PostLikesController {

    private final PostLikesService postLikesService;

    @PostMapping("/{postId}/{userId}")
    @Operation(summary = "게시글 좋아요 API")
    public ResponseEntity<ResponseDTO<?>> like( @Parameter(description = "좋아요를 누를 게시글 ID") @PathVariable Long postId,
                                                @Parameter(description = "좋아요를 누르는 사용자 ID") @PathVariable Long userId) {

        postLikesService.togglePostLike(postId,userId);
        return ApiResponseUtil.ok(SuccessStatus._UPDATE_SUCCESS,"좋아요가 성공적으로 저장되었습니다");
    }

}
