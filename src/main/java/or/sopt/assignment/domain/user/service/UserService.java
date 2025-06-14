package or.sopt.assignment.domain.user.service;

import lombok.RequiredArgsConstructor;
import or.sopt.assignment.domain.user.validator.UserServiceValidator;
import or.sopt.assignment.domain.user.controller.dto.UserCreateRequestDTO;
import or.sopt.assignment.domain.user.entity.User;
import or.sopt.assignment.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserServiceValidator userServiceValidator;

    public Long save(UserCreateRequestDTO request) {

        userServiceValidator.userNameLengthValidation(request.name());

        User user = User.of(request);

        userRepository.save(user);

        return user.getId();
    }
}
