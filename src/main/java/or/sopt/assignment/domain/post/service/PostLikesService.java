package or.sopt.assignment.domain.post.service;

import org.springframework.transaction.annotation.Transactional;

public interface PostLikesService {
    @Transactional
    void togglePostLike(Long postId, Long userId);
}
