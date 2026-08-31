package com.inkblogdb.ddd.presenter.api.v1;

import com.inkblogdb.ddd.application.dto.user.UserDTO;
import com.inkblogdb.ddd.application.usecase.abort.UserNotFondException;
import com.inkblogdb.ddd.application.usecase.user.GetUserUseCase;
import com.inkblogdb.ddd.domain.model.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final GetUserUseCase getUserUseCase;

  @GetMapping("/{userId}")
  public UserDTO getUser(@PathVariable String userId) throws UserNotFondException {
    return getUserUseCase.getUser(new UserId(userId));
  }

}
