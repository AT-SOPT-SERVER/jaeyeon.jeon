package or.sopt.assignment.validator;

public class PostServiceValidator {

    public void titleNotBlankValidate(String title) {
        if (title.isEmpty()){
            /*// 과연 예외를 던지는게 좋을까 -> 예외를 던지게 되면 서비스가 중단됨
            throw new IllegalArgumentException("제목을 입력해주세요");*/

            System.err.println("제목을 입력해주세요");
        }
    }

    public void titleLengthValidate(String title) {
        if (title.length() > 31){
            System.err.println("정해진 글자 수를 초과하였습니다");
        }
    }
}
