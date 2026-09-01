package com.inkblogdb.ddd.domain.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserId addUser(UserName userName) {
    User user = new User(
        UserId.generate(),
        userName
    );
    userRepository.save(user);
    return user.userId();
  }

}
