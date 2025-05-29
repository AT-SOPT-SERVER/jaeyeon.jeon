package or.sopt.assignment.domain.post.validator;

import or.sopt.assignment.domain.post.repository.PostRepository;
import or.sopt.assignment.global.exception.handler.PostHandler;
import or.sopt.assignment.global.infrastructure.LocalDateTimeImpl;
import or.sopt.assignment.global.api.status.ErrorStatus;
import org.springframework.stereotype.Component;

@Component
public class PostServiceValidator {

    private final PostRepository postRepository;
    private final LocalDateTimeImpl localDateTime;

    public PostServiceValidator(PostRepository postRepository, LocalDateTimeImpl localDateTime) {
        this.postRepository = postRepository;
        this.localDateTime = localDateTime;
    }


    public void titleNotBlankValidate(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new PostHandler(ErrorStatus._POST_TITLE_NOT_NULL);
        }
    }

    public void contentNotBlankValidate(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new PostHandler(ErrorStatus._POST_CONTENT_NOT_NULL);
        }
    }

    public void titleLengthValidate(String title) {
        int length = getVisualLength(title);
        if (length > 30){
            throw new PostHandler(ErrorStatus._POST_TITLE_LENGTH);
        }
    }

    public void titleDuplicate(String title) {

        if (postRepository.existsByTitle(title)){
            throw new PostHandler(ErrorStatus._POST_TITLE_DUPLICATE);
        }
    }

    public void validatePostCreationTime() {
        postRepository.findTopByOrderByCreatedAtDesc()
                .filter(post -> post.getCreatedAt() != null)
                .filter(post -> post.getCreatedAt().isAfter(localDateTime.getNow().minusMinutes(3)))
                .ifPresent(post -> {
                    throw new PostHandler(ErrorStatus._POST_TITLE_DUPLICATE);
                });
    }

    public void contentLengthValidate(String content) {
        int length = getVisualLength(content);
        if (length > 1000){
            throw new PostHandler(ErrorStatus._POST_CONTENT_LENGTH);
        }
    }



    public static int getVisualLength(String input) {
            return input.codePointCount(0, input.length());
    }

}
