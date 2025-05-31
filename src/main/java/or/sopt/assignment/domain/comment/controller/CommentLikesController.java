package or.sopt.assignment.domain.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.comment.service.CommentLikesService;
import or.sopt.assignment.global.api.exception.status.SuccessStatus;
import or.sopt.assignment.global.api.reponse.ApiResponseUtil;
import or.sopt.assignment.global.api.reponse.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments/likes")
@Tag(name = "댓글 좋아요 관련 API")
public class CommentLikesController {

    private final CommentLikesService commentLikesService;

    @PatchMapping("/{commentId}/{userId}")
    @Operation(summary = "댓글 좋아요 토글 API")
    public ResponseEntity<ResponseDTO<?>> like(@Parameter(description = "좋아요를 누를 댓글") @PathVariable Long commentId,
                                               @Parameter(description = "좋아요를 누른 사용자")@PathVariable Long userId) {

        commentLikesService.toggleCommentLike(commentId, userId);

        return ApiResponseUtil.ok(SuccessStatus._UPDATE_SUCCESS);
    }
}
