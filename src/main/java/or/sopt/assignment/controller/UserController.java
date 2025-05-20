package or.sopt.assignment.controller;

import or.sopt.assignment.dto.UserCreateRequestDTO;
import or.sopt.assignment.global.reponse.ApiResponse;
import or.sopt.assignment.global.reponse.ResponseDTO;
import or.sopt.assignment.global.status.SuccessStatus;
import or.sopt.assignment.service.UserService;
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
