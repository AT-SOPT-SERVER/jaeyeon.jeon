package or.sopt.assignment.domain.comment.service;

import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
}
