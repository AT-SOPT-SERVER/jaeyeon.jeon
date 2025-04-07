package or.sopt.assignment.service;

import or.sopt.assignment.domain.Post;
import or.sopt.assignment.repository.PostRepository;

import java.util.List;

public class PostService {

    private PostRepository postRepository = new PostRepository();

    public void createPost(Post post){
        postRepository.save(post);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post getPostById(int id){
        return postRepository.findById(id);
    }

    public void deletePostById(int id){
        postRepository.deleteById(id);
    }

    public void update(int updateId, String newTitle) {

        Post findPost = postRepository.findById(updateId);
        postRepository.update(findPost, newTitle);

    }
}
