package or.sopt.assignment.dto;

public class UserCreateRequestDTO {

    private String name;
    private String email;

    public UserCreateRequestDTO() {
    }

    public UserCreateRequestDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
