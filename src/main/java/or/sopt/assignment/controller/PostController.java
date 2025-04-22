package or.sopt.assignment.controller;

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
    public void createPost(@RequestBody PostCreateRequestDTO request){
        postService.createPost(request);
    }

    // 게시글 전체 조회
    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // 게시글 상세 조회
    @GetMapping("/post")
    public PostGetResponseDTO getPostById(@RequestParam Long id) {
        return postService.getPostById(id);
    }

    // 게시글 삭제
    @DeleteMapping("/post")
    public boolean deletePostById(@RequestParam Long deleteId) {
        return postService.deletePostById(deleteId);
    }

    // 게시글 수정
    @PatchMapping("/post")
    public boolean updatePostTitle(@RequestParam Long updateId, String newTitle) {
        return postService.update(updateId, newTitle);
    }

    @GetMapping("/posts/keyword")
    public List<PostGetResponseDTO> searchPostsByKeyword(@RequestParam String keyword) {
        return postService.searchPostsByKeyword(keyword);
    }
}
