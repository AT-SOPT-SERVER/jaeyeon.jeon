package or.sopt.assignment.service;

import or.sopt.assignment.domain.User;
import or.sopt.assignment.dto.UserCreateRequestDTO;
import or.sopt.assignment.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserCreateRequestDTO request) {
        User user = new User(request.getName(),request.getEmail());

        userRepository.save(user);
    }
}
