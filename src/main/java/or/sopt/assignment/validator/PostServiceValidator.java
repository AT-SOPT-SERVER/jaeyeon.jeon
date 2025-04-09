package or.sopt.assignment.validator;

import or.sopt.assignment.repository.PostRepository;

import java.time.Duration;
import java.time.LocalDateTime;

public class PostServiceValidator {

    private final PostRepository postRepository;

    public PostServiceValidator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public boolean titleNotBlankValidate(String title) {
        if (title.isEmpty()){
            /*// 과연 예외를 던지는게 좋을까 -> 예외를 던지게 되면 서비스가 중단됨
            throw new IllegalArgumentException("제목을 입력해주세요");*/

            System.err.println("제목을 입력해주세요");
            return true;
        }

        return false;
    }

    public boolean titleLengthValidate(String title) {
        if (title.length() > 31){
            System.err.println("정해진 글자 수를 초과하였습니다");
            return true;
        }

        return false;
    }

    public boolean titleDuplicate(String title) {

        if (postRepository.isValidate(title)){
            System.err.println("제목이 중복되었습니다");
            return true;
        }
        return false;
    }

    public boolean createdAtValidate() {
        LocalDateTime lastCreatedAt = postRepository.getLastLocalDatetime();
        if (lastCreatedAt != null) {
            Duration duration = Duration.between(lastCreatedAt, LocalDateTime.now());
            if (duration.getSeconds() < 180) {
                System.err.println("게시글을 작성한지 3분이 지나지 않았습니다");
                return true;
            }
        }
        return false;
    }
}
