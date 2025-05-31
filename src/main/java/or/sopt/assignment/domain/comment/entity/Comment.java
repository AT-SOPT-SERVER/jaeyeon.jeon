package or.sopt.assignment.domain.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import or.sopt.assignment.domain.post.entity.Post;
import or.sopt.assignment.domain.user.entity.User;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Integer likesCount;

    public static Comment of(String content, Post post, User user) {
        return Comment.builder()
                .content(content)
                .post(post)
                .user(user)
                .build();
    }

    public static void update(Comment comment,String content) {
        comment.content = content;
    }
}
