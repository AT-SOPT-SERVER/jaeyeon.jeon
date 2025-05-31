package or.sopt.assignment.domain.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.comment.controller.dto.CommentSaveRequestDTO;
import or.sopt.assignment.domain.comment.controller.dto.CommentUpdateRequestDTO;
import or.sopt.assignment.domain.comment.service.CommentService;
import or.sopt.assignment.global.api.exception.status.SuccessStatus;
import or.sopt.assignment.global.api.reponse.ApiResponseUtil;
import or.sopt.assignment.global.api.reponse.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Tag(name = "댓글 관련 API")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save")
    @Operation(summary = "댓글 저장 API")
    public ResponseEntity<ResponseDTO<?>> save(@RequestBody CommentSaveRequestDTO request){
        commentService.save(request);

        return ApiResponseUtil.ok(SuccessStatus._CREATED_SUCCESS,"성공적으로 저장되었습니다");
    }

    @PatchMapping("/update")
    @Operation(summary = "댓글 수정 API")
    public ResponseEntity<ResponseDTO<?>> update(@RequestBody CommentUpdateRequestDTO request){
        commentService.update(request);

        return ApiResponseUtil.ok(SuccessStatus._UPDATE_SUCCESS,"성공적으로 수정 되었습니다");
    }

    // 댓글 삭제

    // 댓글 조회
}
