package or.sopt.assignment.domain.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthCallbackResponse {

    private String code;

    private String error;

    private String error_description;

    private String state;
}