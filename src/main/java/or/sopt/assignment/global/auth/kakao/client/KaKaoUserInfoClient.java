package or.sopt.assignment.global.auth.kakao.client;

import or.sopt.assignment.global.auth.dto.KaKaoUserInfoResponse;
import or.sopt.assignment.global.auth.kakao.config.KaKaoOAuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "kakaoUserInfoClient",
        url = "https://kapi.kakao.com",
        configuration = KaKaoOAuthFeignConfig.class
)
public interface KaKaoUserInfoClient {

    @PostMapping("/v2/user/me")
    KaKaoUserInfoResponse getUserInfo(@RequestHeader("Authorization") String accessToken);
}
