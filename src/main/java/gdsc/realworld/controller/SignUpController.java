package gdsc.realworld.controller;

import gdsc.realworld.entity.User;
import gdsc.realworld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SignUpController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/")
    @ResponseBody
    public String signUpUser(@RequestBody User newUser) {
        String username = newUser.getUsername();
        String password = newUser.getPassword();
        String email = newUser.getEmail();

        if(username.equals("") || password.equals("") || email.equals(""))
            return "failed";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        if (userRepository.findByEmail(email) != null)
            return "failed";

        userRepository.save(user);

        return "success";
    }
}
