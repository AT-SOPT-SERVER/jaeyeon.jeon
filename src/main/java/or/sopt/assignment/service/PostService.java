package or.sopt.assignment.service;

import or.sopt.assignment.domain.Post;
import or.sopt.assignment.repository.PostRepository;
import or.sopt.assignment.util.IdGenerator;

import java.util.List;

public class PostService {

    private final PostRepository postRepository = new PostRepository();
    private final IdGenerator idGenerator = new IdGenerator();

    public void createPost(String title){

        if (title.isEmpty()){
            /*// 과연 예외를 던지는게 좋을까 -> 예외를 던지게 되면 서비스가 중단됨
            throw new IllegalArgumentException("제목을 입력해주세요");*/

            System.err.println("제목을 입력해주세요");
            return;
        }

        if (title.length() > 31){
            System.err.println("정해진 글자 수를 초과하였습니다");
            return;
        }

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

        Post findPost = postRepository.findById(updateId);
        postRepository.update(findPost, newTitle);

        return true;
    }
}
