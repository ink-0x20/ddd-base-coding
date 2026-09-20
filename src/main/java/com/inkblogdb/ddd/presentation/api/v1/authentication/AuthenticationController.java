package com.inkblogdb.ddd.presentation.api.v1.authentication;

import com.inkblogdb.ddd.application.usecase.user.AddUserCommand;
import com.inkblogdb.ddd.application.usecase.authentication.LoginModel;
import com.inkblogdb.ddd.application.usecase.abort.UserNotFoundException;
import com.inkblogdb.ddd.application.usecase.authentication.LoginUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final LoginUseCase loginUseCase;

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.CREATED)
  public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws UserNotFoundException {
    LoginModel loginModel = loginUseCase.login(
        new AddUserCommand(authenticationRequest.userId())
    );
    return new AuthenticationResponse(
        loginModel.token(),
        loginModel.playerId()
    );
  }

}
