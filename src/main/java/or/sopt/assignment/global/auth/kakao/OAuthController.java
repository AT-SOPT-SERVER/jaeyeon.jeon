package or.sopt.assignment.global.auth.kakao;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import or.sopt.assignment.global.auth.kakao.dto.OAuthCallbackResponse;
import or.sopt.assignment.global.auth.kakao.service.OAuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OAuthController {

    private final OAuthService oAuthService;

    @GetMapping("/oauth/kakao")
    @Operation(summary = "카카오 소셜로그인 API",
            description = "카카오로 로그인 요청을 전송합니다. <br><br>" +
                    "카카오 인증서버로 요청을 보내고 그 후에 **백엔드의 주소**로 리다이렉트 됩니다.")
    public void kakaoOAuthCallback(HttpServletResponse response) throws IOException {

        String redirectAddress = oAuthService.requestRedirect();
        response.sendRedirect(redirectAddress);
    }


    @Operation(summary = "카카오 인증서버 토큰 검증 API")
    @GetMapping("/oauth/kakao/callback")
    public void kakaoLogin(@RequestParam("code") String accessCode) {
        oAuthService.kakaoLogin(accessCode);
    }
}
