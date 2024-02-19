package gdsc.realworld.service;

import gdsc.realworld.exception.UserNotFoundException;
import gdsc.realworld.repository.UserRepository;
import gdsc.realworld.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByUserName(String username) {
        return Optional.ofNullable(userRepository.findByUserName(username))
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public User updateUser(User userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        user.setEmail(userDTO.getEmail());
        user.setImage(userDTO.getImage());
        user.setBio(userDTO.getBio());
        return user;
    }

    public User getMemberProfile(String email, String password) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));

        return userOptional.filter(user -> user.getPassword().equals(password))
                .orElseThrow(UserNotFoundException::new);
    }
}
