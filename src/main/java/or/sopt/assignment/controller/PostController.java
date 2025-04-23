package or.sopt.assignment.controller;

import or.sopt.assignment.apiPayLoad.ApiResponse;
import or.sopt.assignment.domain.Post;
import or.sopt.assignment.dto.PostCreateRequestDTO;
import or.sopt.assignment.dto.PostGetResponseDTO;
import or.sopt.assignment.service.PostService;
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
    public ApiResponse<String> createPost(@RequestBody PostCreateRequestDTO request){
        postService.createPost(request);

        return ApiResponse.onSuccess("게시글이 성공적으로 등록되었습니다");
    }

    // 게시글 전체 조회
    @GetMapping("/posts")
    public ApiResponse<List<PostGetResponseDTO>> getAllPosts() {
        List<PostGetResponseDTO> result = postService.getAllPosts();

        return ApiResponse.onSuccess(result);
    }

    // 게시글 상세 조회
    @GetMapping("/post")
    public ApiResponse<PostGetResponseDTO> getPostById(@RequestParam Long id) {
        PostGetResponseDTO result = postService.getPostById(id);

        return ApiResponse.onSuccess(result);
    }

    // 게시글 삭제
    @DeleteMapping("/post")
    public ApiResponse<Boolean> deletePostById(@RequestParam Long deleteId) {
        return ApiResponse.onSuccess(postService.deletePostById(deleteId));
    }

    // 게시글 수정
    @PatchMapping("/post")
    public ApiResponse<Boolean> updatePostTitle(@RequestParam Long updateId, String newTitle) {
        return ApiResponse.onSuccess(postService.update(updateId,newTitle));
    }

    @GetMapping("/posts/keyword")
    public ApiResponse<List<PostGetResponseDTO>> searchPostsByKeyword(@RequestParam String keyword) {
        List<PostGetResponseDTO> result = postService.searchPostsByKeyword(keyword);

        return ApiResponse.onSuccess(result);
    }
}
