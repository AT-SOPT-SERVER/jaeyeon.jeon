package or.sopt.assignment.controller;

import or.sopt.assignment.domain.Post;
import or.sopt.assignment.service.PostService;

import java.util.List;

public class PostController {


    private PostService postService = new PostService();
    private int postId = 1;

    public void createPost(String title){
        Post newPost = new Post(postId++, title);

        postService.createPost(newPost);
    }

    public List<Post> getAllPosts() {

        return postService.getAllPosts();
    }

    public Post getPostById(int id) {

        return postService.getPostById(id);
    }

    public boolean deletePostById(int deleteId) {
        postService.deletePostById(deleteId);

        return true;
    }

    public boolean updatePostTitle(int updateId, String newTitle) {
        postService.update(updateId,newTitle);

        return true;
    }
}
