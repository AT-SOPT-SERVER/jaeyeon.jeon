package or.sopt.assignment.domain.post.dto;

import java.util.List;

public record PostGetResponseListDTO (List<PostGetResponseDTO> dtos){
    public static PostGetResponseListDTO of(List<PostGetResponseDTO> dto) {
        return new PostGetResponseListDTO(dto);
    }
}
