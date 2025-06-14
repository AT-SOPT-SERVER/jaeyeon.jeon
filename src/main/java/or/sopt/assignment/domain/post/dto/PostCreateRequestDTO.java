package or.sopt.assignment.domain.post.dto;

public record PostCreateRequestDTO(String title, String content, Long userId, String tags) {
}
