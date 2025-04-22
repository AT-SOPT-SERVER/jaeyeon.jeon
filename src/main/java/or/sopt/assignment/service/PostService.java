package or.sopt.assignment.service;

import or.sopt.assignment.domain.Post;
import or.sopt.assignment.repository.PostRepository;
import or.sopt.assignment.util.IdGenerator;
import or.sopt.assignment.util.LocalDateTimeImpl;
import or.sopt.assignment.validator.PostServiceValidator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostServiceValidator postServiceValidator;

    public PostService(PostRepository postRepository,
                       PostServiceValidator postServiceValidator) {
        this.postRepository = postRepository;
        this.postServiceValidator = postServiceValidator;
    }

    public void createPost(String title) {

        Post newPost = new Post(title);
        if (createValidate(title)) return;

        postRepository.save(newPost);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post getPostById(Long id){
        return postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다"));
    }

    public boolean deletePostById(Long id){
        postRepository.deleteById(id);

        return true;
    }

    public boolean update(Long updateId, String newTitle) {

        postServiceValidator.titleNotBlankValidate(newTitle);
        postServiceValidator.titleLengthValidate(newTitle);

        Post findPost = postRepository.findById(updateId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다"));

        findPost.update(newTitle);

        postRepository.save(findPost);
        return true;
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        List<Post> result = postRepository.findByTitleContaining(keyword);

        if (result.isEmpty()){
            System.err.println("키워드를 포함한 게시글이 존재하지 않습니다");
        }

        return result;
    }






    private boolean createValidate(String title) {
      /*  if (postServiceValidator.createdAtValidate()) {
            return true;
        }*/
        if (postServiceValidator.titleNotBlankValidate(title)){
            return true;
        }
        if (postServiceValidator.titleLengthValidate(title)){
            return true;
        }
        if (postServiceValidator.titleDuplicate(title)){
            return true;
        }
        return false;
    }
}
