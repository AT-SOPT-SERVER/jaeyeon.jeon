package or.sopt.assignment.domain.post.entity;


import jakarta.persistence.*;
import lombok.*;
import or.sopt.assignment.domain.comment.entity.Comment;
import or.sopt.assignment.domain.post.dto.PostCreateRequestDTO;
import or.sopt.assignment.domain.post.entity.Enum.Tags;
import or.sopt.assignment.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private Tags tags;

    private Integer likesCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;


    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void decreaseLikesCount() {
        if (this.likesCount > 0) {
            this.likesCount--;
        }
    }

    public void increaseLikesCount() {
        this.likesCount++;
    }


    public static Post of(PostCreateRequestDTO postRequestDTO,
                   or.sopt.assignment.global.infrastructure.LocalDateTime localDateTime,
                   User user,
                   Tags tags){

        return Post.builder()
                .title(postRequestDTO.title())
                .createdAt(localDateTime.getNow())
                .user(user)
                .tags(tags)
                .build();
    }
}
