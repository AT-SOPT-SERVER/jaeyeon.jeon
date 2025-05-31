package or.sopt.assignment.domain.comment.service;

import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.comment.repository.CommentLikesRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentLikesServiceImpl implements CommentLikesService {

    private final CommentLikesRepository commentLikesRepository;
}
