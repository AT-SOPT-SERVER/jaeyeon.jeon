package or.sopt.assignment.domain.post.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PostGetResponsePagingListDTO(
        List<PostGetResponseDTO> posts,
        int totalPages,
        int currentPage,
        long totalElements
) {
    public static PostGetResponsePagingListDTO of(Page<PostGetResponseDTO> page, int currentPage) {
        return new PostGetResponsePagingListDTO(
                page.getContent(),
                page.getTotalPages(),
                currentPage,
                page.getTotalElements()
        );
    }
}
