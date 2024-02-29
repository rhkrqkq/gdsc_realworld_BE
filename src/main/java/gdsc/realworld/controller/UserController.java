package gdsc.realworld.controller;

import gdsc.realworld.config.JwtToken;
import gdsc.realworld.domain.SignInDTO;
import gdsc.realworld.entity.User;
import gdsc.realworld.exception.UserNotFoundException;
import gdsc.realworld.config.JwtUtil;
import gdsc.realworld.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.hibernate.query.sqm.tree.SqmNode.log;


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
    public JwtToken signIn(@RequestBody SignInDTO signInDTO) {
        String username = signInDTO.getUsername();
        String password = signInDTO.getPassword();
        JwtToken jwtToken = userService.signIn(username, password);
        log.info("request username = {}, password = {}");
        log.info("jwtToken accessToken = {}, refreshToken = {}");
        return jwtToken;
    }

    @PostMapping("/test")
    public String test() {
        return "success";
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
            Optional<User> user = userService.findUsername(username);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getCurrentUser(HttpServletRequest request) {
        User currentUser = jwtUtil.getCurrentUser(request);
        if (currentUser != null) {
            return ResponseEntity.ok(currentUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
