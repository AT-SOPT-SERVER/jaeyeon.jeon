package or.sopt.assignment.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public String getContent() {
        return content;
    }

    // Constructor
    public Post(String title,String content,LocalDateTime createdAt) {
        this.title = title;
        this.createdAt = createdAt;
        this.content = content;
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

    public void update(String title){
        this.title = title;
    }
}
