package or.sopt.assignment.domain.comment.controller.dto;

public record CommentUpdateRequestDTO(Long commentId, Long userId, String content) {
}
