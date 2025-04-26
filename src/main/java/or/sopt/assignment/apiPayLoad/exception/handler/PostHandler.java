package or.sopt.assignment.apiPayLoad.exception.handler;

import or.sopt.assignment.apiPayLoad.code.BaseErrorCode;
import or.sopt.assignment.apiPayLoad.exception.GeneralException;

public class PostHandler extends GeneralException {

    public PostHandler(BaseErrorCode code) {
        super(code);
    }
}
