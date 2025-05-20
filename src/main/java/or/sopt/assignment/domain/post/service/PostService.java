package or.sopt.assignment.domain.post.service;

import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.post.validator.PostServiceValidator;
import or.sopt.assignment.domain.post.entity.Enum.Tags;
import or.sopt.assignment.domain.post.controller.dto.PostCreateRequestDTO;
import or.sopt.assignment.domain.post.controller.dto.PostGetResponseDTO;
import or.sopt.assignment.domain.post.controller.dto.PostUpdateRequestDTO;
import or.sopt.assignment.domain.post.entity.Post;
import or.sopt.assignment.domain.post.repository.PostRepository;
import or.sopt.assignment.domain.user.entity.User;
import or.sopt.assignment.domain.util.LocalDateTime;
import or.sopt.assignment.global.exception.handler.PostHandler;
import or.sopt.assignment.global.exception.handler.UserHandler;
import or.sopt.assignment.global.status.ErrorStatus;
import or.sopt.assignment.domain.user.repository.UserRepository;
import or.sopt.assignment.domain.util.LocalDateTimeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final PostServiceValidator postServiceValidator;
    private final LocalDateTime localDateTimeImpl;

    private final UserRepository userRepository;


    public Long createPost(PostCreateRequestDTO postRequestDTO) {

        User findUser = findUser(postRequestDTO);
        createValidate(postRequestDTO.title(), postRequestDTO.content());
        Tags postTags;

        try {
            postTags = Tags.valueOf(postRequestDTO.tags().toUpperCase());
        } catch (Exception e) {
            throw new PostHandler(ErrorStatus._POST_TAG_NOT_FOUND);
        }

        Post newPost = new Post(postRequestDTO.title(),
                postRequestDTO.content(),
                localDateTimeImpl.getNow(),
                findUser,
                postTags);

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
        postServiceValidator.contentLengthValidate(postRequestDTO.newContent());

        Post findPost = findPost(updateId);

        findPost.update(postRequestDTO.newTitle(),
                postRequestDTO.newContent());

       return findPost.getId();
    }

    public List<PostGetResponseDTO> searchPostsByKeyword(String keyword) {
        List<Post> result = postRepository.findByTitleContaining(keyword);

        return result.stream().map(
                post -> new PostGetResponseDTO(post.getTitle(),
                        post.getContent(),
                        post.getUser().getName())
        ).toList();
    }

    public List<PostGetResponseDTO> searchByUserName(String name) {

        List<Post> posts = postRepository.findByUserName(name);

        return posts.stream().map(
                post -> new PostGetResponseDTO(
                        post.getTitle(),
                        post.getContent(),
                        post.getUser().getName()
                )
        ).toList();

    }


    public List<PostGetResponseDTO> searchByTags(String tags) {

        Tags postTags;

        try {
            postTags = Tags.valueOf(tags);
        } catch (Exception e) {
            throw new PostHandler(ErrorStatus._POST_TAG_NOT_FOUND);
        }

        List<Post> posts = postRepository.findByTags(postTags);

        return posts.stream().map(
                post -> new PostGetResponseDTO(
                        post.getTitle(),
                        post.getContent(),
                        post.getUser().getName()
                )
        ).toList();
    }



    private Post findPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostHandler(ErrorStatus._POST_NOT_FOUND));
    }

    private void createValidate(String title, String content) {
        /*// API 테스트할때 방해가 돼서 그만...
        postServiceValidator.validatePostCreationTime();*/
        postServiceValidator.contentNotBlankValidate(content);
        postServiceValidator.titleNotBlankValidate(title);
        postServiceValidator.titleLengthValidate(title);
        postServiceValidator.contentLengthValidate(content);
        postServiceValidator.titleDuplicate(title);
    }


    private User findUser(PostCreateRequestDTO postRequestDTO) {
        return userRepository.findById(postRequestDTO.userId())
                .orElseThrow(()->new UserHandler(ErrorStatus._USER_NOT_FOUND));
    }
}
