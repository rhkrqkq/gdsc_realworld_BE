package gdsc.realworld.service;

import gdsc.realworld.entity.User;

import java.util.Optional;

public interface UserFindService {
    Optional<User> findById(long id);
    Optional<User> findByUserName(User username);
}
