package or.sopt.assignment.validator;

import or.sopt.assignment.domain.Post;
import or.sopt.assignment.repository.PostRepository;
import or.sopt.assignment.util.LocalDateTimeImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

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
            throw new IllegalArgumentException("제목없음");
        }
    }
    public void titleLengthValidate(String title) {
        int length = getVisualLength(title);
        if (length > 30){
            throw new IllegalArgumentException("제목 길이 짧음");
        }
    }

    public void titleDuplicate(String title) {

        if (postRepository.existsByTitle(title)){
            throw new IllegalArgumentException("제목중복");
        }
    }

    public void validatePostCreationTime() {
        postRepository.findTopByOrderByCreatedAtDesc()
                .filter(post -> post.getCreatedAt() != null)
                .filter(post -> post.getCreatedAt().isAfter(localDateTime.getNow().minusMinutes(3)))
                .ifPresent(post -> {
                    throw new IllegalArgumentException("3분 안됨");
                });
    }



    public static int getVisualLength(String input) {
            return input.codePointCount(0, input.length());
    }

}
