package or.sopt.assignment.service;

import or.sopt.assignment.domain.User;
import or.sopt.assignment.dto.UserCreateRequestDTO;
import or.sopt.assignment.repository.UserRepository;
import or.sopt.assignment.validator.UserServiceValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserServiceValidator userServiceValidator;

    public UserService(UserRepository userRepository,
                       UserServiceValidator userServiceValidator) {

        this.userRepository = userRepository;
        this.userServiceValidator = userServiceValidator;
    }

    public Long save(UserCreateRequestDTO request) {

        userServiceValidator.userNameLengthValidation(request.name());

        User user = new User(request.name(),request.email());
        userRepository.save(user);

        return user.getId();
    }
}
