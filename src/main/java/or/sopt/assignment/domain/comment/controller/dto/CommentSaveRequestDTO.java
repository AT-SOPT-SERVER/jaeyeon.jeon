package or.sopt.assignment.domain.comment.controller.dto;

public record CommentSaveRequestDTO(String content,
                                    Long postId,
                                    Long userId) {
}
