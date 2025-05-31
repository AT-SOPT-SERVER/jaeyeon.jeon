package or.sopt.assignment.domain.post.controller;

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
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 생성 - 단일 객체를 저장하니까 s를 붙이지 않는다, 난 명사는 무조건 복수형인줄 알았는데 그게 아니구나
    @PostMapping("/post")
    public ResponseEntity<ResponseDTO<?>> createPost(@RequestBody PostCreateRequestDTO request){
        Long result = postService.createPost(request);

        return ApiResponseUtil.ok(SuccessStatus._CREATED_SUCCESS,result);
    }

    // 게시글 전체 조회
    @GetMapping("/posts")
    public ResponseEntity<ResponseDTO<?>> getAllPosts() {

        return ApiResponseUtil.ok(SuccessStatus._READ_SUCCESS,postService.getAllPosts());
    }

    // 게시글 상세 조회
    @GetMapping("/post")
    public ResponseEntity<ResponseDTO<?>> getPostById(@RequestHeader("id") Long id) {
        PostGetResponseDTO result = postService.getPostById(id);

        return ApiResponseUtil.ok(SuccessStatus._CREATED_SUCCESS,result);
    }

    // 게시글 삭제
    @DeleteMapping("/post")
    public ResponseEntity<ResponseDTO<?>> deletePostById(@RequestHeader("id") Long id) {
        postService.deletePostById(id);
        return ApiResponseUtil.ok(SuccessStatus._DELETE_SUCCESS);
    }

    // 게시글 수정
    @PatchMapping("/post")
    public ResponseEntity<ResponseDTO<?>> updatePostTitle(@RequestHeader("id") Long id,
                                   @RequestBody PostUpdateRequestDTO request) {
        Long result = postService.update(id, request);
        return ApiResponseUtil.ok(SuccessStatus._CREATED_SUCCESS,result);
    }

    @GetMapping("/posts/keyword")
    public ResponseEntity<ResponseDTO<?>> searchPostsByKeyword(@RequestParam String keyword) {

        return ApiResponseUtil.ok(SuccessStatus._READ_SUCCESS,postService.searchPostsByKeyword(keyword));
    }

    @GetMapping("/posts/user-name")
    public ResponseEntity<ResponseDTO<?>> searchPostsByUserName(@RequestParam String userName) {
        PostGetResponseListDTO result = postService.searchByUserName(userName);

        return ApiResponseUtil.ok(SuccessStatus._READ_SUCCESS,result);
    }

    @GetMapping("/posts/tags")
    public ResponseEntity<ResponseDTO<?>> searchPostsByTag(@RequestParam String tags) {
        PostGetResponseListDTO result = postService.searchByTags(tags);

        return ApiResponseUtil.ok(SuccessStatus._READ_SUCCESS,result);
    }
}
