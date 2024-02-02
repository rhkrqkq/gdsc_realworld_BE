package gdsc.realworld.controller;

import gdsc.realworld.domain.UserWrapper;
import gdsc.realworld.entity.User;
import gdsc.realworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

}
