package or.sopt.assignment.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;
import or.sopt.assignment.domain.user.entity.User;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class PostLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static PostLikes of(Post post, User user) {
        return PostLikes.builder()
                .post(post)
                .user(user)
                .build();
    }
}
