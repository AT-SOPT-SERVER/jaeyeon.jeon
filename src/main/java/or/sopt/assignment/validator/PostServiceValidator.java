package or.sopt.assignment.validator;

import or.sopt.assignment.apiPayLoad.code.status.ErrorStatus;
import or.sopt.assignment.apiPayLoad.exception.handler.PostHandler;
import or.sopt.assignment.repository.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class PostServiceValidator {

    private final PostRepository postRepository;

    public PostServiceValidator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public boolean titleNotBlankValidate(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new PostHandler(ErrorStatus._POST_TITLE_EXSIST);
        }

        return false;
    }
    public boolean titleLengthValidate(String title) {
        int length = getVisualLength(title);
        if (length > 30){
            throw new PostHandler(ErrorStatus._POST_TITLE_LENGTH);
        }

        return false;
    }

    public boolean titleDuplicate(String title) {

        if (postRepository.existsByTitle(title)){
            throw new PostHandler(ErrorStatus._POST_TITLE_DUPLICATE);
        }
        return false;
    }

/*    public boolean createdAtValidate() {
        LocalDateTime lastCreatedAt = postRepository.getLastLocalDatetime();
        if (lastCreatedAt != null) {
            Duration duration = Duration.between(lastCreatedAt, LocalDateTime.now());
            if (duration.getSeconds() < 180) {
                System.err.println("게시글을 작성한지 3분이 지나지 않았습니다");
                return true;
            }
        }
        return false;
    }*/


    public static int getVisualLength(String input) {
            return input.codePointCount(0, input.length());
    }

}
