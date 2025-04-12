package or.sopt.assignment.controller;

import or.sopt.assignment.domain.Post;
import or.sopt.assignment.dto.PostRequestDTO;
import or.sopt.assignment.service.PostService;
import or.sopt.assignment.util.IdGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class PostController {

    private final PostService postService = new PostService();

    // 게시글 생성 - 단일 객체를 저장하니까 s를 붙이지 않는다, 난 명사는 무조건 복수형인줄 알았는데 그게 아니구나
    @PostMapping("/post")
    public void createPost(@RequestBody PostRequestDTO request){
        postService.createPost(request.getTitle());
    }

    // 게시글 전체 조회
    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // 게시글 상세 조회
    public Post getPostById(int id) {
        return postService.getPostById(id);
    }

    // 게시글 삭제
    public boolean deletePostById(int deleteId) {
        return postService.deletePostById(deleteId);
    }

    // 게시글 수정
    public boolean updatePostTitle(int updateId, String newTitle) {
        return postService.update(updateId, newTitle);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postService.searchPostsByKeyword(keyword);
    }
}
