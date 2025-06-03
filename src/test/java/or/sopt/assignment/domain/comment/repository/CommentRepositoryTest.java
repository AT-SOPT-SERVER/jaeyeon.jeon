package or.sopt.assignment.domain.comment.repository;

import or.sopt.assignment.domain.comment.entity.Comment;
import or.sopt.assignment.domain.post.entity.Post;
import or.sopt.assignment.domain.post.entity.Enum.Tags;
import or.sopt.assignment.domain.post.repository.PostRepository;
import or.sopt.assignment.domain.user.entity.Role;
import or.sopt.assignment.domain.user.entity.SocialType;
import or.sopt.assignment.domain.user.entity.User;
import or.sopt.assignment.domain.user.repository.UserRepository;
import or.sopt.assignment.global.config.QuerydslConfig;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
@ActiveProfiles("test")
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepositoryImpl commentRepositoryCustom;

    @Autowired
    TestEntityManager testEntityManager;

    private Long testPostId;
    private final int COMMENT_COUNT = 5;


}