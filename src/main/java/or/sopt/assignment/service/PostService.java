package or.sopt.assignment.service;

import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.Post;
import or.sopt.assignment.dto.PostCreateRequestDTO;
import or.sopt.assignment.dto.PostGetResponseDTO;
import or.sopt.assignment.repository.PostRepository;
import or.sopt.assignment.util.LocalDateTimeImpl;
import or.sopt.assignment.validator.PostServiceValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final PostServiceValidator postServiceValidator;
    private final LocalDateTimeImpl localDateTimeImpl;

    public PostService(PostRepository postRepository,
                       PostServiceValidator postServiceValidator,
                       LocalDateTimeImpl localDateTimeImpl) {
        this.postRepository = postRepository;
        this.postServiceValidator = postServiceValidator;
        this.localDateTimeImpl = localDateTimeImpl;
    }

    public void createPost(PostCreateRequestDTO postRequestDTO) {

        createValidate(postRequestDTO.title());
        Post newPost = new Post(postRequestDTO.title(),localDateTimeImpl.getNow());

        postRepository.save(newPost);
    }

    public List<PostGetResponseDTO> getAllPosts(){
        List<Post> findPosts = postRepository.findAll();

        return findPosts.stream().map(
                post -> new PostGetResponseDTO(post.getTitle(),post.getId())
        ).toList();
    }

    public PostGetResponseDTO getPostById(Long id){
        Post findPost = findPost(id);

        return new PostGetResponseDTO(findPost.getTitle(),findPost.getId());
    }

    public Boolean deletePostById(Long id){

        Post post = findPost(id);
        postRepository.delete(post);

        return Boolean.TRUE;
    }

    public boolean update(Long updateId, String newTitle) {

        postServiceValidator.titleNotBlankValidate(newTitle);
        postServiceValidator.titleLengthValidate(newTitle);

        Post findPost = findPost(updateId);

        findPost.update(newTitle);

        return true;
    }

    public List<PostGetResponseDTO> searchPostsByKeyword(String keyword) {
        List<Post> result = postRepository.findByTitleContaining(keyword);

        if (result.isEmpty()){
            System.err.println("키워드를 포함한 게시글이 존재하지 않습니다");
        }

        return result.stream().map(
                post -> new PostGetResponseDTO(post.getTitle(),post.getId())
        ).toList();
    }




    private Post findPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다"));
    }

    private void createValidate(String title) {

        postServiceValidator.validatePostCreationTime();
        postServiceValidator.titleNotBlankValidate(title);
        postServiceValidator.titleLengthValidate(title);
        postServiceValidator.titleDuplicate(title);
    }
}
