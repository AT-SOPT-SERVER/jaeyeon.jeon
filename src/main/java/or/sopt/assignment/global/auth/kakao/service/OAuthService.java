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
}
