package or.sopt.assignment.global.auth.jwt.exception;

import or.sopt.assignment.global.api.exception.status.ErrorStatus;
import or.sopt.assignment.global.api.exception.CustomException;

public class TokenHandler extends CustomException {
    public TokenHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
