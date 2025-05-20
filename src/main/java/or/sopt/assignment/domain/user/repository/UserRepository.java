package or.sopt.assignment.domain.user.repository;

import or.sopt.assignment.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
