package or.sopt.assignment.domain.comment.service;

import org.springframework.transaction.annotation.Transactional;

public interface CommentLikesService {
    @Transactional
    void toggleCommentLike(Long commentId, Long userId);
}
