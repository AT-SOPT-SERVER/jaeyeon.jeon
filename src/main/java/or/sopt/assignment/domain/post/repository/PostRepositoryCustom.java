package or.sopt.assignment.domain.post.repository;


import or.sopt.assignment.domain.post.entity.Post;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> findAllOrderedByCreatedAtDescV2();

    List<Post> findByUserNameV2(String name);
}
