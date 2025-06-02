package or.sopt.assignment.domain.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.post.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

import static or.sopt.assignment.domain.comment.entity.QComment.comment;
import static or.sopt.assignment.domain.post.entity.QPost.post;
import static or.sopt.assignment.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<Post> findAllOrderedByCreatedAtDescV2() {
        return queryFactory
                .selectFrom(post)
                .orderBy(post.createdAt.desc())
                .fetch();
    }


    @Override
    public List<Post> findByUserNameV2(String name) {
        return queryFactory
                .selectFrom(post)
                .join(post.user, user).fetchJoin()
                .leftJoin(post.comments, comment).fetchJoin()
                .where(user.name.eq(name))
                .distinct()
                .fetch();

    }


}
