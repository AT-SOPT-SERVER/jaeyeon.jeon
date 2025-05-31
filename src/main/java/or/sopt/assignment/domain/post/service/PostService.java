package or.sopt.assignment.domain.post.service;

import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.comment.controller.dto.CommentGetResponseDTO;
import or.sopt.assignment.domain.comment.entity.Comment;
import or.sopt.assignment.domain.comment.repository.CommentRepository;
import or.sopt.assignment.domain.post.dto.PostGetResponseListDTO;
import or.sopt.assignment.domain.post.validator.PostServiceValidator;
import or.sopt.assignment.domain.post.entity.Enum.Tags;
import or.sopt.assignment.domain.post.dto.PostCreateRequestDTO;
import or.sopt.assignment.domain.post.dto.PostGetResponseDTO;
import or.sopt.assignment.domain.post.dto.PostUpdateRequestDTO;
import or.sopt.assignment.domain.post.entity.Post;
import or.sopt.assignment.domain.post.repository.PostRepository;
import or.sopt.assignment.domain.user.entity.User;
import or.sopt.assignment.global.api.exception.status.CommonErrorStatus;
import or.sopt.assignment.global.api.exception.handler.PostHandler;
import or.sopt.assignment.domain.user.exception.UserHandler;
import or.sopt.assignment.global.infrastructure.LocalDateTime;
import or.sopt.assignment.domain.user.repository.UserRepository;
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
    private final CommentRepository commentRepository;


    public Long createPost(PostCreateRequestDTO postRequestDTO) {

        User findUser = findUser(postRequestDTO);
        createValidate(postRequestDTO.title(), postRequestDTO.content());
        Tags postTags;

        try {
            postTags = Tags.valueOf(postRequestDTO.tags().toUpperCase());
        } catch (Exception e) {
            throw new PostHandler(CommonErrorStatus._POST_TAG_NOT_FOUND);
        }

        Post newPost = Post.of(postRequestDTO, localDateTimeImpl, findUser, postTags);

        postRepository.save(newPost);

        return newPost.getId();
    }

    public PostGetResponseListDTO getAllPosts(){
        List<Post> findPosts = postRepository.findAllByOrderByCreatedAtDesc();


        List<PostGetResponseDTO> dtos = findPosts.stream()
                .map(post -> {
                    List<Comment> comments = commentRepository.findByPostId(post.getId());
                    List<CommentGetResponseDTO> commentGetResponseDTOS = getCommentGetResponseDTOS(comments);
                    return PostGetResponseDTO.from(post, commentGetResponseDTOS);
                })
                .toList();

        return PostGetResponseListDTO.of(dtos);
    }

    public PostGetResponseDTO getPostById(Long id){
        Post findPost = findPost(id);

        List<Comment> comments = commentRepository.findByPostId(findPost.getId());
        List<CommentGetResponseDTO> commentGetResponseDTOS = getCommentGetResponseDTOS(comments);

        return PostGetResponseDTO.from(findPost, commentGetResponseDTOS);
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

    public PostGetResponseListDTO searchPostsByKeyword(String keyword) {
        List<Post> findPosts = postRepository.findByTitleContaining(keyword);

        List<PostGetResponseDTO> dtos = findPosts.stream()
                .map(post -> {
                    List<Comment> comments = commentRepository.findByPostId(post.getId());
                    List<CommentGetResponseDTO> commentGetResponseDTOS = getCommentGetResponseDTOS(comments);
                    return PostGetResponseDTO.from(post, commentGetResponseDTOS);
                })
                .toList();

        return PostGetResponseListDTO.of(dtos);
    }

    public PostGetResponseListDTO searchByUserName(String name) {

        List<Post> findPosts = postRepository.findByUserName(name);

        List<PostGetResponseDTO> dtos = findPosts.stream()
                .map(post -> {
                    List<Comment> comments = commentRepository.findByPostId(post.getId());
                    List<CommentGetResponseDTO> commentGetResponseDTOS = getCommentGetResponseDTOS(comments);
                    return PostGetResponseDTO.from(post, commentGetResponseDTOS);
                })
                .toList();

        return PostGetResponseListDTO.of(dtos);

    }


    public PostGetResponseListDTO searchByTags(String tags) {

        Tags postTags;

        try {
            postTags = Tags.valueOf(tags);
        } catch (Exception e) {
            throw new PostHandler(CommonErrorStatus._POST_TAG_NOT_FOUND);
        }

        List<Post> findPosts = postRepository.findByTags(postTags);

        List<PostGetResponseDTO> dtos = findPosts.stream()
                .map(post -> {
                    List<Comment> comments = commentRepository.findByPostId(post.getId());
                    List<CommentGetResponseDTO> commentGetResponseDTOS = getCommentGetResponseDTOS(comments);
                    return PostGetResponseDTO.from(post, commentGetResponseDTOS);
                })
                .toList();

        return PostGetResponseListDTO.of(dtos);
    }



    private Post findPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostHandler(CommonErrorStatus._POST_NOT_FOUND));
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
                .orElseThrow(()->new UserHandler(CommonErrorStatus._USER_NOT_FOUND));
    }

    private static List<CommentGetResponseDTO> getCommentGetResponseDTOS(List<Comment> comments) {
        return comments.stream()
                .map(CommentGetResponseDTO::from)
                .toList();
    }
}
