package or.sopt.assignment.dto;

public class PostGetResponseDTO {

    private Long id;

    private String title;

    public PostGetResponseDTO() {
    }

    public PostGetResponseDTO(String title, Long id) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    // 스프링에서 JSON으로 역직렬화를 하려면 반드시 getter메서드가 필요하다
    public String getTitle() {
        return title;
    }
}
