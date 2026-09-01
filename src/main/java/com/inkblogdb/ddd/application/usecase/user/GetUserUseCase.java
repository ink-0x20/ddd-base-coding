package com.inkblogdb.ddd.application.usecase.user;

import com.inkblogdb.ddd.application.dto.user.UserDTO;
import com.inkblogdb.ddd.application.usecase.abort.UserNotFondException;
import com.inkblogdb.ddd.domain.model.user.User;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetUserUseCase {

  private final UserRepository userRepository;

  public UserDTO getUser(UserId userId) throws UserNotFondException {
    Optional<User> user = userRepository.findById(userId);
    if (user.isEmpty()) {
      throw new UserNotFondException("ユーザーが見つかりません");
    }
    return new UserDTO(
        user.get().userId().toString(),
        user.get().userName().value()
    );
  }

}
