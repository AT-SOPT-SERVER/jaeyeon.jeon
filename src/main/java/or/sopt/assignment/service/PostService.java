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

       postServiceValidator.titleNotBlankValidate(title);
       postServiceValidator.titleLengthValidate(title);

       // 해당 로직을 Validate로 빼면 의존관계가 이상해짐: 과연 의존관계냐 일관성이냐
        if(postRepository.isValidate(title)){
            System.err.println("게시글의 제목이 중복되었습니다");
            return;
        }

        Post newPost = new Post(idGenerator.idGenerate(), title);
        postRepository.save(newPost);
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
}
