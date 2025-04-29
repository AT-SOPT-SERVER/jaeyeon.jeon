package or.sopt.assignment.dto;

import java.util.List;

public record PostCreateRequestDTO(String title, String content, Long userId, String tags) {
}
