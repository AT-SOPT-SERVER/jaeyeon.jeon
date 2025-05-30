package or.sopt.assignment.global.auth.kakao.client;


import or.sopt.assignment.global.auth.kakao.config.KaKaoOAuthFeignConfig;
import or.sopt.assignment.global.auth.kakao.dto.KaKaoOAuthTokenDTO;
import or.sopt.assignment.global.auth.kakao.dto.OAuthCallbackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "kakaoOAuthClient",
        url = "https://kauth.kakao.com",
        configuration = KaKaoOAuthFeignConfig.class
)
public interface KaKaoOAuthClient {


    @GetMapping("/oauth/authorize")
    OAuthCallbackResponse getCode(@RequestParam("client_id") String client_id,
                                  @RequestParam("redirect_uri") String redirect_uri,
                                  @RequestParam("response_type") String response_type);


    @PostMapping("/oauth/token")
    KaKaoOAuthTokenDTO getToken(@RequestParam("grant_type") String grant_type,
                                @RequestParam("client_id") String client_id,
                                @RequestParam("redirect_uri") String redirect_uri,
                                @RequestParam("code") String code);


}
