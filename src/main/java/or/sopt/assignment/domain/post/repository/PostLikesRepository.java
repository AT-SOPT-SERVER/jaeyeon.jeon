package or.sopt.assignment.domain.post.repository;

import or.sopt.assignment.domain.post.entity.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
}
