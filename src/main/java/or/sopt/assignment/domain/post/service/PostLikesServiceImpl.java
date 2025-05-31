package or.sopt.assignment.domain.post.service;

import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.post.repository.PostLikesRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikesServiceImpl implements PostLikesService {

    private final PostLikesRepository postLikesRepository;
}
