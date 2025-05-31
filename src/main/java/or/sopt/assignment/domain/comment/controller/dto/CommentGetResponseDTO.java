package or.sopt.assignment.domain.comment.controller.dto;

import or.sopt.assignment.domain.comment.entity.Comment;

public record CommentGetResponseDTO(String content) {

    public static CommentGetResponseDTO from(Comment comment) {
        return new CommentGetResponseDTO(comment.getContent());
    }
}
