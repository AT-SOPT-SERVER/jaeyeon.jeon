package or.sopt.assignment.domain.post.entity;


import jakarta.persistence.*;
import lombok.*;
import or.sopt.assignment.domain.post.controller.dto.PostCreateRequestDTO;
import or.sopt.assignment.domain.post.entity.Enum.Tags;
import or.sopt.assignment.domain.user.entity.User;

import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }


    public static Post of(PostCreateRequestDTO postRequestDTO,
                   or.sopt.assignment.global.port.LocalDateTime localDateTime,
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
