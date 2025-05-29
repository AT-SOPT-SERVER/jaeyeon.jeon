package or.sopt.assignment.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import or.sopt.assignment.domain.user.controller.dto.UserCreateRequestDTO;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;


    public static User of(UserCreateRequestDTO request) {

        return User.builder()
                .name(request.name())
                .email(request.email())
                .build();
    }

}
