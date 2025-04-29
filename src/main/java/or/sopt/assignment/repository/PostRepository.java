package or.sopt.assignment.repository;

import or.sopt.assignment.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContaining(String keyword);

    boolean existsByTitle(String title);

    Optional<Post> findTopByOrderByCreatedAtDesc();

    List<Post> findAllByOrderByCreatedAtDesc();
}
