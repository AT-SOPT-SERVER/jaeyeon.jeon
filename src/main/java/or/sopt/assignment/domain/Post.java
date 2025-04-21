package or.sopt.assignment.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private int id;

    private String title;

    private LocalDateTime createdAt;

    // Constructor
    public Post(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Post(int id, String title, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
    }

    public Post() {

    }

    // Helper Method
    public int getId() {
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
