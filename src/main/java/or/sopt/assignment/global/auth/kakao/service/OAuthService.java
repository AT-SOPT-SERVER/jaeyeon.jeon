package or.sopt.assignment.global.auth.kakao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import or.sopt.assignment.global.auth.dto.KaKaoUserInfoResponse;
import or.sopt.assignment.global.auth.kakao.client.KaKaoOAuthClient;
import or.sopt.assignment.global.auth.kakao.client.KaKaoUserInfoClient;
import or.sopt.assignment.global.auth.kakao.dto.KaKaoOAuthTokenDTO;
import or.sopt.assignment.global.auth.kakao.dto.OAuthCallbackResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * 서비스 서버가 카카오 인증 서버로 인가 코드 받기를 요청합니다.
 *  - 이때 feign을 통해 카카오 인증서버로 요청을 전달하고
 *
 * 카카오 인증 서버가 사용자에게 인증을 요청합니다.
 * 사용자 클라이언트에 유효한 카카오계정 세션이 있거나, 카카오톡 인앱 브라우저에서의 요청인 경우 4단계로 넘어갑니다.
 * 사용자가 카카오계정으로 로그인합니다.
 * 카카오 인증 서버가 사용자에게 동의 화면을 출력하여 인가를 위한 사용자 동의를 요청합니다.
 * 동의 화면은 서비스 앱(애플리케이션)의 동의항목 설정으로 구성됩니다.
 * 사용자가 필수 동의항목과, 이 외의 원하는 동의항목에 동의한 뒤 [동의하고 계속하기] 버튼을 누릅니다.
 * 카카오 인증 서버는 서비스 서버의 Redirect URI로 인가 코드를 전달합니다.
 *  - 이렇게 로그인이 완료되면 인증코드를 포함한 데이터가 반환된다
 *
 *
 * 그러면 그 다음 우리는 해당 인증코드를 이용하여 회원정보를 가져오면 됨
 *
 * */

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthService {

    private final KaKaoOAuthClient kaKaoOAuthClient;
    private final KaKaoUserInfoClient kaKaoUserInfoClient;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;


    public String requestRedirect() {
        return String.format(
                "https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code",
                clientId, redirectUri
        );
    }


    public void kakaoLogin(String accessCode) {

        KaKaoOAuthTokenDTO authorizationCode = kaKaoOAuthClient.getToken(
                "authorization_code",
                clientId,
                redirectUri,
                accessCode
        );

        KaKaoUserInfoResponse userInfo = kaKaoUserInfoClient.getUserInfo(
                "Bearer "+authorizationCode.getAccess_token());

    }


    public OAuthCallbackResponse getOAuthCode() {
        OAuthCallbackResponse code = kaKaoOAuthClient.getCode(
                clientId, redirectUri, "code");
        log.info("OAuth code: {}", code);

        log.info("OAuth code: {}", code.getCode());
        return code;
    }
}
