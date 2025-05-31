package or.sopt.assignment.domain.post.repository;

import or.sopt.assignment.domain.post.entity.Post;
import or.sopt.assignment.domain.post.entity.PostLikes;
import or.sopt.assignment.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {


    PostLikes findByUserAndPost(User user, Post post);
}
