package or.sopt.assignment.domain.post.dto;

import or.sopt.assignment.domain.comment.controller.dto.CommentGetResponseDTO;
import or.sopt.assignment.domain.post.entity.Post;

import java.util.List;

public record PostGetResponseDTO(String title, String content, String name, List<CommentGetResponseDTO> comments) {

    public static PostGetResponseDTO from(Post findPost, List<CommentGetResponseDTO> comments) {
        return new PostGetResponseDTO(
                findPost.getTitle(),
                findPost.getContent(),
                findPost.getUser().getName(),
                comments);
    }
}