package or.sopt.assignment.service;

import or.sopt.assignment.domain.Post;
import or.sopt.assignment.repository.PostRepository;
import or.sopt.assignment.util.IdGenerator;
import or.sopt.assignment.validator.PostServiceValidator;

import java.util.List;

public class PostService {

    private final PostRepository postRepository = new PostRepository();
    private final IdGenerator idGenerator = new IdGenerator();
    private final PostServiceValidator postServiceValidator = new PostServiceValidator();

    public void createPost(String title){

       postServiceValidator.titleValidate(title);
       postServiceValidator.contentValidate(title);

        Post newPost = new Post(idGenerator.idGenerate(), title);
        postRepository.save(newPost);
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post getPostById(int id){
        return postRepository.findById(id);
    }

    public boolean deletePostById(int id){
        postRepository.deleteById(id);
        return true;
    }

    public boolean update(int updateId, String newTitle) {

        postServiceValidator.titleValidate(newTitle);
        postServiceValidator.contentValidate(newTitle);

        Post findPost = postRepository.findById(updateId);
        postRepository.update(findPost, newTitle);

        return true;
    }
}
