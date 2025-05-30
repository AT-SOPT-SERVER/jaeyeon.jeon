package or.sopt.assignment.global.auth.kakao.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import or.sopt.assignment.domain.user.entity.Role;
import or.sopt.assignment.domain.user.entity.User;
import or.sopt.assignment.domain.user.repository.UserRepository;
import or.sopt.assignment.global.auth.dto.KaKaoUserInfoResponse;
import or.sopt.assignment.global.auth.jwt.JWTUtil;
import or.sopt.assignment.global.auth.kakao.client.KaKaoOAuthClient;
import or.sopt.assignment.global.auth.kakao.client.KaKaoUserInfoClient;
import or.sopt.assignment.global.auth.kakao.dto.KaKaoOAuthTokenDTO;
import or.sopt.assignment.global.config.JWTConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthService {

    private final KaKaoOAuthClient kaKaoOAuthClient;
    private final KaKaoUserInfoClient kaKaoUserInfoClient;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTUtil jwtUtil;
    private final JWTConfig jwtConfig;


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


    public void kakaoLogin(String accessCode, HttpServletResponse response) {

        KaKaoOAuthTokenDTO authorizationCode = kaKaoOAuthClient.getToken(
                "authorization_code",
                clientId,
                redirectUri,
                accessCode
        );

        KaKaoUserInfoResponse userInfo = kaKaoUserInfoClient.getUserInfo(
                "Bearer "+authorizationCode.getAccess_token());

        Boolean userExist = userRepository.existsByEmail(userInfo.getKakao_account().getEmail());

        if (userExist == Boolean.FALSE) {
            User newUser = User.builder()
                    .name(userInfo.getProperties().getNickname())
                    .password(bCryptPasswordEncoder.encode("kakaoPassword"))
                    .email(userInfo.getKakao_account().getEmail())
                    .role(Role.ROLE_USER)
                    .build();

            userRepository.save(newUser);
        }

        User byEmail = userRepository.findByEmail(userInfo.getKakao_account().getEmail());

        String access = jwtUtil.createJwt("access", byEmail.getId(), byEmail.getRole().toString(), jwtConfig.getAccessTokenValidityInSeconds());
        response.setHeader("access", access);

    }
}
