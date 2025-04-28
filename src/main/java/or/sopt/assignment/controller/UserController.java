package or.sopt.assignment.controller;

import or.sopt.assignment.apiPayLoad.ApiResponse;
import or.sopt.assignment.dto.UserCreateRequestDTO;
import or.sopt.assignment.service.UserService;
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
    public ApiResponse<String> save(@RequestBody UserCreateRequestDTO request) {
        userService.save(request);

        return ApiResponse.onSuccess("저장이 성공적으로 완료되었습니다");
    }
}
