package or.sopt.assignment.domain.comment.repository;

import or.sopt.assignment.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
