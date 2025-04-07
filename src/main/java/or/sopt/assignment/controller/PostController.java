package or.sopt.assignment.controller;

import or.sopt.assignment.domain.Post;
import or.sopt.assignment.service.PostService;
import or.sopt.assignment.util.IdGenerator;

import java.util.List;

public class PostController {


    private final PostService postService = new PostService();

    // 게시글 생성
    public void createPost(String title){
        postService.createPost(title);
    }

    // 게시글 전체 조회
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
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
