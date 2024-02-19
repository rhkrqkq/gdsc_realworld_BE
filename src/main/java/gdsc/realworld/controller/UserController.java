package gdsc.realworld.controller;

import gdsc.realworld.entity.User;
import gdsc.realworld.exception.UserNotFoundException;
import gdsc.realworld.login.JwtUtil;
import gdsc.realworld.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity signupUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/users/login")
    public ResponseEntity login(@RequestBody User user, HttpServletRequest request) {
        String email = user.getEmail();
        String password = user.getPassword();

        try {
            User loginMember = userService.getMemberProfile(email, password);

            String token = jwtUtil.generateJwt(email);

            return ResponseEntity.ok(token);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PutMapping("/user")
    public ResponseEntity updateUser(@RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/profiles/{username}")
    public ResponseEntity getProfile(@RequestParam String username) {
        try {
            User user = userService.findByUserName(username);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
