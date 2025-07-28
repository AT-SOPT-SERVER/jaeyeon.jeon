package or.sopt.assignment.domain.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static or.sopt.assignment.domain.comment.entity.QComment.comment;
import static or.sopt.assignment.domain.post.entity.QPost.post;
import static or.sopt.assignment.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    /**
     * 다양한 조건을 지닐 수 있는 정렬 조회 메서드입니다
     *
     * 이런 메서드의 경우, 다양하게 확장될 수 있는 가능성을 지니고 있는데,
     * 일반적인 JPQL을 이용해 사용할 경우 확장에 불리하다고 생각합니다.
     * 또한 이러한 메서드는 페이징/슬라이싱 으로의 확장 가능성이 존재하는데 그럴 경우 QueryDSL이 강제됩니다.
     *
     * 그래서 쿼리의 최적화와는 거리가 있겠지만, 유지보수 측면에서 QueryDSL 을 통해 리팩터링 해야한다고 생각합니다
     * */
    @Override
    public List<Post> findAllOrderedByCreatedAtDescV2() {
        return queryFactory
                .selectFrom(post)
                .orderBy(post.createdAt.desc())
                .fetch();
    }


    /**
     * N+1을 유발하는 메서드입니다
     * 일대다로 매핑되어 있는 회원과 게시글에 대해서 조회 시, N+1이 발생 할 수 있습니다
     *
     * fetch join을 활용하여 N+1을 해결하는데, 이때 게시글의 댓글 역시 한 번의 쿼리를 통해 가져와
     * 단 한번의 쿼리로 성능을 최적화 합니다.
     *
     * 물론 이와 같은 기능은 JPQL로도 해결이 가능합니다만, 위와같은 의미로
     * 쿼리가 너무 복잡해지면 JPQL로의 유지보수는 힘들 수 있습니다.
     * */
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


    @Override
    public Page<Post> findAllWithComments(Pageable pageable) {
        List<Post> content = queryFactory
                .selectFrom(post)
                .leftJoin(post.comments, comment).fetchJoin()
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .distinct()
                .fetch();

        // 총 개수는 페치조인을 제외한 별도 카운트로 조회
        long total = queryFactory
                .selectFrom(post)
                .fetchCount();

        return PageableExecutionUtils.getPage(content, pageable, () -> total);
    }


}
