package or.sopt.assignment.global.auth.kakao.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import or.sopt.assignment.domain.user.entity.Role;
import or.sopt.assignment.domain.user.entity.SocialType;
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


/**
 * ## 현재 해당 클래스의 책임 FIXME
 *
 * 1. 소셜로그인을 위해 카카오서버와의 커뮤니케이션
 * 2. 실질적인 로그인 로직
 *
 * 만약 일반로그인을 지원하는 서버였다면, 이렇게 하나의 클래스에 큰 책임을 물지 않았을 것 같음
 * 하지만 현재 소셜로그인만 지원하는 상황에서 로그인 로직까지 담당하는건 큰 무리같다고 생각하지 않음
 *
 * 하지만 추후 자체 로그인으로 디벨롭되는 경우 어차피 분화될 기능인 것도 사실이기 때문에
 * 책임을 분리할 필요는 충분히 존재
 * 그러면 해당 OAuth Class는 서비스가 아닌 Util 클래스로 전환하여 의존관계를 유지하도록 할 수 있음
 *
 * 결론적으로 디벨롭 포인트는
 *
 * 1. 로그인 로직과 서드파티에서 데이터를 가져오는 로직을 분리
 * 2. 다른 서드파티가 추가될 것을 고려한, 확장성 있는 설계로 리팩터링 돼야함
 * 3. 클라이언트에서의 헤더접근 문제 해결 (밑에서 서술)
 * */
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

        KaKaoOAuthTokenDTO authorizationCode = getKaKaoOAuthTokenDTO(accessCode);

        KaKaoUserInfoResponse userInfo = kaKaoUserInfoClient.getUserInfo(
                "Bearer "+authorizationCode.getAccess_token());

        Boolean userExist = userRepository.existsByEmail(userInfo.getKakao_account().getEmail());

        if (userExist == Boolean.FALSE) {
            User newUser = User.builder()
                    .name(userInfo.getProperties().getNickname())
                    .password(bCryptPasswordEncoder.encode("kakaoPassword"))
                    .email(userInfo.getKakao_account().getEmail())
                    .role(Role.ROLE_USER)
                    .socialType(SocialType.KAKAO)
                    .build();

            userRepository.save(newUser);
        }

        User byEmail = userRepository.findByEmail(userInfo.getKakao_account().getEmail());

        /**
         * 현재 해당 로직에는 몇가지 문제가 존재함
         * 리다이렉트로 시작한 요청이기 떄문에 클라이언트 단에서 헤더에 있는 응답 정보를 갖고 있지 않음 -> 헤더로 토큰을 주면 토큰 파싱이 힘들다
         *
         * 해결책으로는
         * 1. 우리가 브라우저 쿠키에 직접 두 토큰을 담아서 보냄
         * 2. 클라이언트 단에서 다시 요청을 받아서 보냄 (이때부턴 fetch든, axios든 클라이언트에서 응답을 관리함)
         * 3. 해당 응답에 헤더에 다시 access 토큰을 받아서 던진다
         *
         * 이렇게 하면 되는데...그리고 지금까진 이렇게 해왔는데...과연 이게 최선일까 싶긴 함
         *
         * cf) 그리고 리프레시 토큰은 따로 구현하지 않았습니다. 위의 문제도 해결 안되기도 했고 리프레시를 넣는 순간
         *          1. 리프레시 저장소 구축 및 검증로직
         *          2. 리프레시 로테이트 로직 구현
         *          3. 저 위의 문제 해결
         *
         *      등 해결할게 너무 많아보여서,.. 회원은 이정도로 마무리 하겠습니다
         * */
        String access = jwtUtil.createJwt("access", byEmail.getId(), byEmail.getRole().toString(), jwtConfig.getAccessTokenValidityInSeconds());
        response.setHeader("access-token", access);
    }


    private KaKaoOAuthTokenDTO getKaKaoOAuthTokenDTO(String accessCode) {

        return kaKaoOAuthClient.getToken(
                "authorization_code",
                clientId,
                redirectUri,
                accessCode
        );
    }
}
