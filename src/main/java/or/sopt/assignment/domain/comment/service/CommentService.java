package or.sopt.assignment.domain.comment.service;

import or.sopt.assignment.domain.comment.controller.dto.CommentDeleteRequestDTO;
import or.sopt.assignment.domain.comment.controller.dto.CommentSaveRequestDTO;
import or.sopt.assignment.domain.comment.controller.dto.CommentUpdateRequestDTO;
import org.springframework.transaction.annotation.Transactional;

public interface CommentService {
    @Transactional
    void save(CommentSaveRequestDTO request);

    @Transactional
    void update(CommentUpdateRequestDTO request);

    @Transactional
    void delete(CommentDeleteRequestDTO request);
}
