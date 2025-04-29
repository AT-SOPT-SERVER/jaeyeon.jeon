package or.sopt.assignment.global.exception.handler;

import or.sopt.assignment.global.exception.CustomException;
import or.sopt.assignment.global.status.ErrorStatus;

public class UserHandler extends CustomException {
    public UserHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
