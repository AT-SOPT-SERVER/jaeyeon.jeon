package or.sopt.assignment.domain.user.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import or.sopt.assignment.domain.user.entity.Role;
import or.sopt.assignment.domain.user.entity.SocialType;
import or.sopt.assignment.domain.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class AuthController {

    /**
     * 세미나 때 진행한 내용입니다
     * */

    @GetMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request) {

        /**
         * http://soptUser:sopt1234@localhost:8080/login
         * 고전적인 URL 빙식 회원 인증
         * */
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null && authHeader.startsWith("Basic ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("헤더 이상해");
        }

        String decodeString = authHeader.substring("Basic ".length());
        byte[] decodeByteString = Base64.getDecoder().decode(decodeString);
        String credentials = new String(decodeByteString, StandardCharsets.UTF_8);

        String[] parts = credentials.split(":");

        String username = parts[0];
        String password = parts[1];

        if (username.equals("soptUser") && password.equals("sopt1234")) {
            return ResponseEntity.ok("인증 성공");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패");
        }
    }


    @GetMapping("/set-cookie")
    public ResponseEntity<String> setCookie(HttpServletRequest request, HttpServletResponse response) {

        String username = "userSopt";
        String password = "sopt1234";

        Cookie usernameCookie = new Cookie("username", username);
        Cookie passwordCookie = new Cookie("password", password);

        // 쿠키의 허용범위를 지정
        usernameCookie.setPath("/");
        passwordCookie.setPath("/");

        response.addCookie(usernameCookie);
        response.addCookie(passwordCookie);

        return ResponseEntity.ok("쿠키 다 구웠어");
    }


    @GetMapping("/get-cookie")
    public ResponseEntity<String> getCookie(@CookieValue("username") String userId,
                                            @CookieValue("password") String password) {
        return ResponseEntity.ok("받은 쿠키: " + "userId=" + userId + ", password=" + password);
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginPost(HttpServletRequest request) {

        // 요청값 하드코딩
        String userId = "userSopt";
        String password = "sopt1234";

        if (userId.equals("userSopt") && password.equals("sopt1234")) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", new User(1L,password,"sopt","sopt@naver.com", Role.ROLE_USER, SocialType.COMMON));

            return ResponseEntity.ok("세션 저장 완료");
        }

        // 그리고 그 후에 sessionId를 클라에 반환(쿠키를 통해)

        throw new IllegalArgumentException("너 회원 아니잖아너 회원 아니잖아너 회원 아니잖아너 회원 아니잖아너 회원 아니잖아너 회원 아니잖아너 회원 아니잖아");
    }
}
