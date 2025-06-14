package or.sopt.assignment.domain.comment.service;

import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.comment.entity.Comment;
import or.sopt.assignment.domain.comment.entity.CommentLikes;
import or.sopt.assignment.domain.comment.exception.CommentErrorStatus;
import or.sopt.assignment.domain.comment.exception.CommentHandler;
import or.sopt.assignment.domain.comment.repository.CommentLikesRepository;
import or.sopt.assignment.domain.comment.repository.CommentRepository;
import or.sopt.assignment.domain.user.entity.User;
import or.sopt.assignment.domain.user.exception.UserErrorStatus;
import or.sopt.assignment.domain.user.exception.UserHandler;
import or.sopt.assignment.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentLikesServiceImpl implements CommentLikesService {

    private final CommentLikesRepository commentLikesRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void toggleCommentLike(Long commentId, Long userId) {

        User user = findUser(userId);
        Comment comment = findComment(commentId);

        CommentLikes commentLikes = commentLikesRepository.findByUserAndComment(user, comment);

        if (commentLikes != null) {
            comment.decreaseLikesCount();
            commentLikesRepository.delete(commentLikes);
        } else {
            CommentLikes newCommentLikes = CommentLikes.from(user, comment);
            comment.increaseLikesCount();
            commentLikesRepository.save(newCommentLikes);
        }
    }


    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new UserHandler(UserErrorStatus.USER_NOT_FOUND));
    }

    private Comment findComment(Long commentId) {
        return commentRepository.findByIdForUpdate(commentId)
                .orElseThrow(() -> new CommentHandler(CommentErrorStatus.COMMENT_NOT_FOUND));
    }
}
