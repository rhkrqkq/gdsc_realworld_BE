package gdsc.realworld.domain;

import lombok.Getter;

@Getter
public class UserDTO {

    private Long id;
    private String password;
    private String username;
    private String email;
    private String bio;
    private String image = "https://api.realworld.io/images/smiley-cyrus.jpg";

}
