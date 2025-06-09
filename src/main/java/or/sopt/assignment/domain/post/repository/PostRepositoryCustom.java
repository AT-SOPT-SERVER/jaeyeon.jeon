package or.sopt.assignment.domain.post.repository;


import or.sopt.assignment.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> findAllOrderedByCreatedAtDescV2();

    List<Post> findByUserNameV2(String name);

    Page<Post> findAllWithComments(Pageable pageable);
}
