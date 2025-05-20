package or.sopt.assignment.domain.post.controller.dto;

public record PostCreateRequestDTO(String title, String content, Long userId, String tags) {
}
