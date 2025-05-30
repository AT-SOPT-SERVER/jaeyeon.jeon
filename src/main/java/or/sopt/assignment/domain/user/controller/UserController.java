package or.sopt.assignment.domain.user.controller;

import or.sopt.assignment.domain.user.controller.dto.UserCreateRequestDTO;
import or.sopt.assignment.domain.user.service.UserService;
import or.sopt.assignment.global.api.reponse.ApiResponse;
import or.sopt.assignment.global.api.reponse.ResponseDTO;
import or.sopt.assignment.global.api.exception.status.SuccessStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseDTO<?>> save(@RequestBody UserCreateRequestDTO request) {
        Long result = userService.save(request);

        return ApiResponse.ok(SuccessStatus._CREATED_SUCCESS,result);
    }
}
