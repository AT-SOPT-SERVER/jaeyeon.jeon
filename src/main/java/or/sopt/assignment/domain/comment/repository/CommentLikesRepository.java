package or.sopt.assignment.domain.comment.repository;

import or.sopt.assignment.domain.comment.entity.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
}
