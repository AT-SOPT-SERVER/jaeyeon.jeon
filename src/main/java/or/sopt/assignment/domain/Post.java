package or.sopt.assignment.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

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
