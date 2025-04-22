package or.sopt.assignment.dto;

public class PostCreateRequestDTO {

    private String title;

    public PostCreateRequestDTO(String title) {
        this.title = title;
    }

    public PostCreateRequestDTO() {
    }

    public String getTitle() {
        return title;
    }
}
