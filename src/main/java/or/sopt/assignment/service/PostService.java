package or.sopt.assignment.service;

import or.sopt.assignment.domain.Post;
import or.sopt.assignment.domain.User;
import or.sopt.assignment.dto.PostCreateRequestDTO;
import or.sopt.assignment.dto.PostGetResponseDTO;
import or.sopt.assignment.dto.PostUpdateRequestDTO;
import or.sopt.assignment.global.exception.handler.UserHandler;
import or.sopt.assignment.global.status.ErrorStatus;
import or.sopt.assignment.repository.PostRepository;
import or.sopt.assignment.repository.UserRepository;
import or.sopt.assignment.util.LocalDateTimeImpl;
import or.sopt.assignment.validator.PostServiceValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final PostServiceValidator postServiceValidator;
    private final LocalDateTimeImpl localDateTimeImpl;

    private final UserRepository userRepository;

    public PostService(PostRepository postRepository,
                       PostServiceValidator postServiceValidator,
                       LocalDateTimeImpl localDateTimeImpl,
                       UserRepository userRepository) {

        this.postRepository = postRepository;
        this.postServiceValidator = postServiceValidator;
        this.localDateTimeImpl = localDateTimeImpl;
        this.userRepository = userRepository;
    }

    public Long createPost(PostCreateRequestDTO postRequestDTO) {

        User findUser = findUser(postRequestDTO);
        createValidate(postRequestDTO.title(), postRequestDTO.content());

        Post newPost = new Post(postRequestDTO.title(),
                postRequestDTO.content(),
                localDateTimeImpl.getNow(),
                findUser);

        postRepository.save(newPost);

        return newPost.getId();
    }

    public List<PostGetResponseDTO> getAllPosts(){
        List<Post> findPosts = postRepository.findAllByOrderByCreatedAtDesc();

        return findPosts.stream().map(
                post -> new PostGetResponseDTO(
                        post.getTitle(),
                        post.getContent(),
                        post.getUser().getName())
        ).toList();
    }

    public PostGetResponseDTO getPostById(Long id){
        Post findPost = findPost(id);

        return new PostGetResponseDTO(
                findPost.getTitle(),
                findPost.getContent(),
                findPost.getUser().getName());
    }

    public void deletePostById(Long id){

        Post post = findPost(id);
        postRepository.delete(post);
    }

    public Long update(Long updateId, PostUpdateRequestDTO postRequestDTO) {

        postServiceValidator.titleNotBlankValidate(postRequestDTO.newTitle());
        postServiceValidator.titleLengthValidate(postRequestDTO.newContent());

        Post findPost = findPost(updateId);

        findPost.update(postRequestDTO.newTitle(),
                postRequestDTO.newContent());

       return findPost.getId();
    }

    public List<PostGetResponseDTO> searchPostsByKeyword(String keyword) {
        List<Post> result = postRepository.findByTitleContaining(keyword);

        if (result.isEmpty()){
            System.err.println("키워드를 포함한 게시글이 존재하지 않습니다");
        }

        return result.stream().map(
                post -> new PostGetResponseDTO(post.getTitle(),
                        post.getContent(),
                        post.getUser().getName())
        ).toList();
    }




    private Post findPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다"));
    }

    private void createValidate(String title, String content) {
        /*// API 테스트할때 방해가 돼서 그만...
        postServiceValidator.validatePostCreationTime();*/
        postServiceValidator.contentNotBlankValidate(content);
        postServiceValidator.titleNotBlankValidate(title);
        postServiceValidator.titleLengthValidate(title);
        postServiceValidator.titleDuplicate(title);
    }


    private User findUser(PostCreateRequestDTO postRequestDTO) {
        return userRepository.findById(postRequestDTO.userId())
                .orElseThrow(()->new UserHandler(ErrorStatus._USER_NOT_FOUND));
    }
}
