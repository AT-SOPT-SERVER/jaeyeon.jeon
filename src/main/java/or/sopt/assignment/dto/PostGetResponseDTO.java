package or.sopt.assignment.dto;

public class PostGetResponseDTO {

    private String title;

    public PostGetResponseDTO() {
    }

    public PostGetResponseDTO(String title) {
        this.title = title;
    }

    // 스프링에서 JSON으로 역직렬화를 하려면 반드시 getter메서드가 필요하다
    public String getTitle() {
        return title;
    }
}
