package or.sopt.assignment.domain.post.service;

import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.post.entity.Post;
import or.sopt.assignment.domain.post.entity.PostLikes;
import or.sopt.assignment.domain.post.repository.PostLikesRepository;
import or.sopt.assignment.domain.post.repository.PostRepository;
import or.sopt.assignment.domain.user.entity.User;
import or.sopt.assignment.domain.user.exception.UserErrorStatus;
import or.sopt.assignment.domain.user.exception.UserHandler;
import or.sopt.assignment.domain.user.repository.UserRepository;
import or.sopt.assignment.global.api.exception.handler.PostHandler;
import or.sopt.assignment.global.api.exception.status.CommonErrorStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostLikesServiceImpl implements PostLikesService {

    private final PostLikesRepository postLikesRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;



    @Override
    @Transactional
    public void togglePostLike(Long postId, Long userId) {

        User user = findUser(userId);
        Post post = findPost(postId);

        PostLikes postLikes = postLikesRepository.findByUserAndPost(user, post);

        if (postLikes != null) {
            post.decreaseLikesCount();
            postLikesRepository.delete(postLikes);
        } else {
            PostLikes newPostLikes = PostLikes.of(post, user);
            post.increaseLikesCount();
            postLikesRepository.save(newPostLikes);
        }
    }



    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new UserHandler(UserErrorStatus.USER_NOT_FOUND));
    }

    private Post findPost(Long postId) {
        return postRepository.findByIdForUpdate(postId)
                .orElseThrow(()-> new PostHandler(CommonErrorStatus._POST_NOT_FOUND));
    }

}
