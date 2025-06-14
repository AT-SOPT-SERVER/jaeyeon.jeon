package or.sopt.assignment.global.api.exception.handler;

import or.sopt.assignment.global.api.exception.CustomException;
import or.sopt.assignment.global.api.exception.status.ErrorStatus;

public class PostHandler extends CustomException {

    public PostHandler(ErrorStatus errorDTO) {

        super(errorDTO);

    }
}
