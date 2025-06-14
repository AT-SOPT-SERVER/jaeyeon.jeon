package or.sopt.assignment.domain.comment.exception;

import or.sopt.assignment.global.api.exception.CustomException;
import or.sopt.assignment.global.api.exception.status.ErrorStatus;

public class CommentHandler extends CustomException {
    public CommentHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
