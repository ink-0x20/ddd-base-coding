package com.inkblogdb.ddd.application.usecase.user;

import com.inkblogdb.ddd.application.dto.user.UserDTO;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.user.UserName;
import com.inkblogdb.ddd.domain.model.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddUserUseCase {

  private final UserService userService;

  public UserDTO addUser(UserName userName) {
    UserId userId = userService.addUser(userName);
    return new UserDTO(
        userId.toString(),
        userName.value()
    );
  }

}
