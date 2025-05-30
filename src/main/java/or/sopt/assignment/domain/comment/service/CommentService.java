package or.sopt.assignment.domain.comment.service;

import or.sopt.assignment.domain.comment.controller.dto.CommentSaveRequestDTO;
import org.springframework.transaction.annotation.Transactional;

public interface CommentService {
    @Transactional
    void save(CommentSaveRequestDTO request);
}
