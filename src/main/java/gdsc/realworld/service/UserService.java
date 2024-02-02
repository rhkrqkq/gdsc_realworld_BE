package gdsc.realworld.service;

import gdsc.realworld.domain.UserDTO;
import gdsc.realworld.exception.UserNotFoundException;
import gdsc.realworld.repository.UserRepository;
import gdsc.realworld.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

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

}
