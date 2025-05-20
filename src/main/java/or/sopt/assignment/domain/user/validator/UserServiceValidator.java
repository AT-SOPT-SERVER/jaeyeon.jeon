package or.sopt.assignment.domain.user.validator;

import or.sopt.assignment.global.exception.handler.UserHandler;
import or.sopt.assignment.global.status.ErrorStatus;
import org.springframework.stereotype.Component;

@Component
public class UserServiceValidator {

    public void userNameLengthValidation(String name) {
        if (name.length() > 10) {
            throw new UserHandler(ErrorStatus._USER_NAME_LENGTH);
        }
    }
}
