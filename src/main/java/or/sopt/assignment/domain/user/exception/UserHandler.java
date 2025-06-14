package or.sopt.assignment.domain.user.exception;

import or.sopt.assignment.global.api.exception.CustomException;
import or.sopt.assignment.global.api.exception.status.ErrorStatus;

public class UserHandler extends CustomException {
    public UserHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
