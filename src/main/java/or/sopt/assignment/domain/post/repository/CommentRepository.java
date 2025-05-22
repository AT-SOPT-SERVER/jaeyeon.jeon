package or.sopt.assignment.domain.post.repository;

import or.sopt.assignment.domain.post.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
