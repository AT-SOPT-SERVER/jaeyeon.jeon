package or.sopt.assignment.domain.comment.repository;

import or.sopt.assignment.domain.comment.entity.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> findByPostIdWithUser(Long postId);
}
