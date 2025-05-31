package or.sopt.assignment.domain.comment.repository;

import jakarta.persistence.LockModeType;
import or.sopt.assignment.domain.comment.entity.Comment;
import or.sopt.assignment.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public List<Comment> findByPostId(Long postId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Comment c WHERE c.id = :commentId")
    Optional<Comment> findByIdForUpdate(Long commentId);
}
