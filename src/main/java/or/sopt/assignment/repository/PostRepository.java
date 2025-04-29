package or.sopt.assignment.repository;

import or.sopt.assignment.domain.Post;
import or.sopt.assignment.domain.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
