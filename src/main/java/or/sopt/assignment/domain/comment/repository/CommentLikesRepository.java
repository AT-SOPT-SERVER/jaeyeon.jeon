package or.sopt.assignment.domain.comment.repository;

import or.sopt.assignment.domain.comment.entity.Comment;
import or.sopt.assignment.domain.comment.entity.CommentLikes;
import or.sopt.assignment.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
    CommentLikes findByUserAndComment(User user, Comment comment);
}
