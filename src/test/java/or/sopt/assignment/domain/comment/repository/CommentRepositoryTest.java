package or.sopt.assignment.domain.comment.repository;

import or.sopt.assignment.domain.comment.entity.Comment;
import or.sopt.assignment.domain.post.repository.PostRepository;
import or.sopt.assignment.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepositoryImpl commentRepositoryCustom;

    @Test
    void 일반_조회는_N_개의_쿼리를_발생시킨다() {
        List<Comment> comments = commentRepository.findByPostId(1L);

        for (Comment comment : comments) {
            // LAZY 로딩으로 인해 여기서 user 쿼리가 N번 발생
            System.out.println(comment.getUser().getName());
        }
    }

    @Test
    void fetchJoin은_N1을_해결한다() {
        List<Comment> comments = commentRepositoryCustom.findByPostIdWithUser(1L);

        for (Comment comment : comments) {
            // 이미 fetch join 되어 있으므로 쿼리 발생하지 않음
            System.out.println(comment.getUser().getName());
        }
    }
}