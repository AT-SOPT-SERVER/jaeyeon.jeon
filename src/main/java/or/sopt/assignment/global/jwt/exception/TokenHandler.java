package or.sopt.assignment.global.jwt.exception;

import or.sopt.assignment.global.api.status.ErrorStatus;
import or.sopt.assignment.global.exception.CustomException;

public class TokenHandler extends CustomException {
    public TokenHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
