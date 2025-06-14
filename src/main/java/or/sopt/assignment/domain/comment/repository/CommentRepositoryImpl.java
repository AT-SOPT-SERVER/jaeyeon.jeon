package or.sopt.assignment.domain.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.comment.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

import static or.sopt.assignment.domain.comment.entity.QComment.comment;
import static or.sopt.assignment.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Comment> findByPostIdWithUser(Long postId) {
        return queryFactory
                .selectFrom(comment)
                .join(comment.user, user).fetchJoin()
                .where(comment.post.id.eq(postId))
                .fetch();
    }
}
