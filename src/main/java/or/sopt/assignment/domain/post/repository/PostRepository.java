package or.sopt.assignment.domain.post.repository;

import jakarta.persistence.LockModeType;
import or.sopt.assignment.domain.post.entity.Enum.Tags;
import or.sopt.assignment.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContaining(String keyword);

    List<Post> findByUserName(String name);

    boolean existsByTitle(String title);

    Optional<Post> findTopByOrderByCreatedAtDesc();

    List<Post> findAllByOrderByCreatedAtDesc();

    List<Post> findByTags(Tags tags);

    /**
     * 게시글을 비관적 락을 걸고 조회합니다
     *
     * 이를 통해 좋아요와 같은 기능에서 발생 할 수 있는 동시성 이슈를 제어합니다
     * */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Post p WHERE p.id = :postId")
    Optional<Post> findByIdForUpdate(Long postId);
}
