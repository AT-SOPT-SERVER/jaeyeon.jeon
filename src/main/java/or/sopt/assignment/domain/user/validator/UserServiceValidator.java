package or.sopt.assignment.domain.user.validator;

import or.sopt.assignment.global.api.exception.status.CommonErrorStatus;
import or.sopt.assignment.global.api.exception.handler.UserHandler;
import org.springframework.stereotype.Component;

@Component
public class UserServiceValidator {

    public void userNameLengthValidation(String name) {
        if (name.length() > 10) {
            throw new UserHandler(CommonErrorStatus._USER_NAME_LENGTH);
        }
    }
}
