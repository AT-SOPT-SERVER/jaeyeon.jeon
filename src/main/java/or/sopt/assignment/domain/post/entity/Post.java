package or.sopt.assignment.domain.post.entity;


import jakarta.persistence.*;
import or.sopt.assignment.domain.post.entity.Enum.Tags;
import or.sopt.assignment.domain.user.entity.User;

import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private Tags tags;

    public User getUser() {
        return user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public String getContent() {
        return content;
    }

    public Tags getTags() {
        return tags;
    }

    // Constructor
    public Post(String title,String content,LocalDateTime createdAt,User user,Tags tags) {
        this.title = title;
        this.createdAt = createdAt;
        this.content = content;
        this.user = user;
        this.tags = tags;
    }

    public Post() {

    }

    // Helper Method
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
