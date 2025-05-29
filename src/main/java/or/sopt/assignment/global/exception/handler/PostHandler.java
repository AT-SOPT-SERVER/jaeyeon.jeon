package or.sopt.assignment.global.exception.handler;

import or.sopt.assignment.global.exception.CustomException;
import or.sopt.assignment.global.api.status.ErrorStatus;

public class PostHandler extends CustomException {

    public PostHandler(ErrorStatus errorDTO) {

        super(errorDTO);

    }
}
