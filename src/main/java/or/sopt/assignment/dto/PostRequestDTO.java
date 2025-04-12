package or.sopt.assignment.dto;

public class PostRequestDTO {

    private String title;

    public PostRequestDTO(String title) {
        this.title = title;
    }

    public PostRequestDTO() {
    }

    public String getTitle() {
        return title;
    }
}
