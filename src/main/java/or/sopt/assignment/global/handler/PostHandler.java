package or.sopt.assignment.global.handler;

import or.sopt.assignment.global.errorStatus.ErrorStatus;

public class PostHandler extends CustomException {

    public PostHandler(ErrorStatus errorDTO) {

        super(errorDTO);

    }
}
