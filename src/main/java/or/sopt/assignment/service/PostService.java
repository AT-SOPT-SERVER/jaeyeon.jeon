package or.sopt.assignment.service;

import or.sopt.assignment.domain.Post;
import or.sopt.assignment.repository.PostRepository;
import or.sopt.assignment.util.IdGenerator;
import or.sopt.assignment.validator.PostServiceValidator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class PostService {

    private final PostRepository postRepository = new PostRepository();
    private final IdGenerator idGenerator = new IdGenerator();
    private final PostServiceValidator postServiceValidator = new PostServiceValidator(postRepository);

    public void createPost(String title){

        LocalDateTime now = LocalDateTime.now();

        if (createValidate(title)) return;

        Post newPost = new Post(idGenerator.idGenerate(), title, now);
        postRepository.save(newPost);

        System.out.println("✅ 게시글이 성공적으로 저장되었습니다!");
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post getPostById(int id){
        Post result = postRepository.findById(id);

        if(result == null){
            System.err.println("해당하는 게시글이 존재하지 않습니다");
        }
        return result;
    }

    public boolean deletePostById(int id){
        postRepository.deleteById(id);
        return true;
    }

    public boolean update(int updateId, String newTitle) {

        postServiceValidator.titleNotBlankValidate(newTitle);
        postServiceValidator.titleLengthValidate(newTitle);

        Post findPost = postRepository.findById(updateId);
        postRepository.update(findPost, newTitle);

        return true;
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        List<Post> result = postRepository.searchPostsByKeyword(keyword);

        if (result.isEmpty()){
            System.err.println("키워드를 포함한 게시글이 존재하지 않습니다");
        }

        return result;
    }






    private boolean createValidate(String title) {
        if (postServiceValidator.createdAtValidate()) {
            return true;
        }
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
