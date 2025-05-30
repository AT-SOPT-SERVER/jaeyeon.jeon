package or.sopt.assignment.domain.comment.service;

import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.comment.controller.dto.CommentSaveRequestDTO;
import or.sopt.assignment.domain.comment.entity.Comment;
import or.sopt.assignment.domain.comment.exception.CommentErrorStatus;
import or.sopt.assignment.domain.comment.exception.CommentHandler;
import or.sopt.assignment.domain.comment.repository.CommentRepository;
import or.sopt.assignment.domain.post.entity.Post;
import or.sopt.assignment.domain.post.repository.PostRepository;
import or.sopt.assignment.domain.user.entity.User;
import or.sopt.assignment.domain.user.exception.UserErrorStatus;
import or.sopt.assignment.domain.user.exception.UserHandler;
import or.sopt.assignment.domain.user.repository.UserRepository;
import or.sopt.assignment.global.api.exception.handler.PostHandler;
import or.sopt.assignment.global.api.exception.status.CommonErrorStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    @Override
    public void save(CommentSaveRequestDTO request) {

        String content = request.content();
        if (content.length()>300){
            throw new CommentHandler(CommentErrorStatus.COMMENT_TOO_LONG);
        }

        User findUser = userRepository.findById(request.userId())
                .orElseThrow(()-> new UserHandler(UserErrorStatus.USER_NOT_FOUND));

        Post findPost = postRepository.findById(request.postId())
                .orElseThrow(()-> new PostHandler(CommonErrorStatus._POST_NOT_FOUND));

        Comment newComment = Comment.of(request.content(), findPost, findUser);

        commentRepository.save(newComment);
    }
}
