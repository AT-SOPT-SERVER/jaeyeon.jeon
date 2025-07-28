package or.sopt.assignment.domain.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import or.sopt.assignment.domain.post.dto.PostCreateRequestDTO;
import or.sopt.assignment.domain.post.dto.PostGetResponseDTO;
import or.sopt.assignment.domain.post.dto.PostGetResponseListDTO;
import or.sopt.assignment.domain.post.service.PostService;
import or.sopt.assignment.domain.post.dto.PostUpdateRequestDTO;
import or.sopt.assignment.global.api.reponse.ResponseDTO;
import or.sopt.assignment.global.api.exception.status.SuccessStatus;
import or.sopt.assignment.global.api.reponse.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "게시글 관련 API")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 생성 - 단일 객체를 저장하니까 s를 붙이지 않는다, 난 명사는 무조건 복수형인줄 알았는데 그게 아니구나
    @PostMapping("/post")
    @Operation(summary = "게시글 생성 API")
    public ResponseEntity<ResponseDTO<?>> createPost(@RequestBody PostCreateRequestDTO request){
        Long result = postService.createPost(request);

        return ApiResponseUtil.ok(SuccessStatus._CREATED_SUCCESS,result);
    }

    // 게시글 전체 조회
    @GetMapping("/posts")
    @Operation(summary = "게시글 전체조회 API")
    public ResponseEntity<ResponseDTO<?>> getAllPosts() {

        return ApiResponseUtil.ok(SuccessStatus._READ_SUCCESS,postService.getAllPosts());
    }

    @GetMapping("/posts/paging")
    @Operation(summary = "게시글 페이징 전체조회 API",
    description = "게시글을 **페이지**로 조회하는 API 입니다 <br>" +
            "10개 단위로 반환되며, 최신순으로 정렬되어 반환됩니다")
    public ResponseEntity<ResponseDTO<?>> getAllPostsPaging(
            @Parameter(description = "조회하는 페이지를 입력해주세요") @RequestParam(defaultValue = "0") int page) {

        return ApiResponseUtil.ok(SuccessStatus._READ_SUCCESS,postService.getAllPostsByPaging(page));
    }

    // 게시글 상세 조회
    @GetMapping("/post")
    @Operation(summary = "게시글 상세조회 API")
    public ResponseEntity<ResponseDTO<?>> getPostById(@RequestHeader("id") Long id) {
        PostGetResponseDTO result = postService.getPostById(id);

        return ApiResponseUtil.ok(SuccessStatus._CREATED_SUCCESS,result);
    }

    // 게시글 삭제
    @DeleteMapping("/post")
    @Operation(summary = "게시글 삭제 API")
    public ResponseEntity<ResponseDTO<?>> deletePostById(@RequestHeader("id") Long id) {
        postService.deletePostById(id);
        return ApiResponseUtil.ok(SuccessStatus._DELETE_SUCCESS);
    }

    // 게시글 수정
    @PatchMapping("/post")
    @Operation(summary = "게시글 수정 API")
    public ResponseEntity<ResponseDTO<?>> updatePostTitle(@RequestHeader("id") Long id,
                                   @RequestBody PostUpdateRequestDTO request) {
        Long result = postService.update(id, request);
        return ApiResponseUtil.ok(SuccessStatus._CREATED_SUCCESS,result);
    }

    @GetMapping("/posts/keyword")
    @Operation(summary = "게시글 키워드로 찾기 API")
    public ResponseEntity<ResponseDTO<?>> searchPostsByKeyword(@RequestParam String keyword) {

        return ApiResponseUtil.ok(SuccessStatus._READ_SUCCESS,postService.searchPostsByKeyword(keyword));
    }

    @GetMapping("/v1/posts/user-name")
    @Operation(summary = "게시글 생성자로 찾기 API")
    public ResponseEntity<ResponseDTO<?>> searchPostsByUserName(@RequestParam String userName) {
        PostGetResponseListDTO result = postService.searchByUserName(userName);

        return ApiResponseUtil.ok(SuccessStatus._READ_SUCCESS,result);
    }

    @GetMapping("/v2/posts/user-name")
    @Operation(summary = "게시글 생성자로 찾기 API",
    description = "**N+1 issue를 해결**한 API 입니다. V1과 쿼리를 비교합니다")
    public ResponseEntity<ResponseDTO<?>> searchPostsByUserNameV2(@RequestParam String userName) {
        PostGetResponseListDTO result = postService.searchByUserNameV2(userName);

        return ApiResponseUtil.ok(SuccessStatus._READ_SUCCESS,result);
    }

    @GetMapping("/posts/tags")
    @Operation(summary = "게시글 태그로 찾기 API")
    public ResponseEntity<ResponseDTO<?>> searchPostsByTag(@RequestParam String tags) {
        PostGetResponseListDTO result = postService.searchByTags(tags);

        return ApiResponseUtil.ok(SuccessStatus._READ_SUCCESS,result);
    }
}
