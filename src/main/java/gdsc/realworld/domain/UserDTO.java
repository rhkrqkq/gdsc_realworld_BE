package gdsc.realworld.domain;

import gdsc.realworld.entity.User;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDTO {

    private Long id;

    private String password;

    private String username;

    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    private String bio;
    private String image = "https://api.realworld.io/images/smiley-cyrus.jpg";

    @Builder
    public UserDTO(Long id, String password, String username, String email, String bio, String image) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.image = image;
    }

}
