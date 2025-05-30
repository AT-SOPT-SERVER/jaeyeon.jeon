package or.sopt.assignment.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.comment.service.CommentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성, 300자로 제한, 게시글 상세 조회 시 같이 조회됨

    // 댓글 수정

    // 댓글 삭제

    // 댓글 조회
}
