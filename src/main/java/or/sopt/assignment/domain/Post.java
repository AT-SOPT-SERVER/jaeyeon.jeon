package or.sopt.assignment.domain;


import java.time.LocalDateTime;

public class Post {


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

    // Helper Method
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void update(String title){
        this.title = title;
    }
}
