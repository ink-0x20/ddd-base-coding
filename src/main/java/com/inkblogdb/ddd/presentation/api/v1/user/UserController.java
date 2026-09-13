package com.inkblogdb.ddd.presentation.api.v1.user;

import com.inkblogdb.ddd.application.usecase.abort.ExistsUserException;
import com.inkblogdb.ddd.application.usecase.user.AddUserUseCase;
import com.inkblogdb.ddd.domain.model.user.UserId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final AddUserUseCase addUserUseCase;

  @PostMapping("/signup")
  @ResponseStatus(HttpStatus.CREATED)
  public void signup(@Valid @RequestBody UserRequest userRequest) throws ExistsUserException {
    addUserUseCase.addUser(
        new UserId(userRequest.userId())
    );
  }

}
