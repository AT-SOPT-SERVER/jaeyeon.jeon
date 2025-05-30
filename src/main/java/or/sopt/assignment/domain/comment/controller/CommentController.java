package or.sopt.assignment.domain.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.comment.controller.dto.CommentSaveRequestDTO;
import or.sopt.assignment.domain.comment.service.CommentService;
import or.sopt.assignment.global.api.exception.status.SuccessStatus;
import or.sopt.assignment.global.api.reponse.ApiResponse;
import or.sopt.assignment.global.api.reponse.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Tag(name = "댓글 관련 API")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성, 300자로 제한, 게시글 상세 조회 시 같이 조회됨
    @PostMapping("/save")
    @Operation(summary = "댓글 저장 API")
    public ResponseEntity<ResponseDTO<?>> save(@RequestBody CommentSaveRequestDTO request){
        commentService.save(request);

        return ApiResponse.ok(SuccessStatus._CREATED_SUCCESS,"성공적으로 저장되었습니다");
    }

    // 댓글 수정

    // 댓글 삭제

    // 댓글 조회
}
