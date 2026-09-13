package com.inkblogdb.ddd.presentation.api.v1.authentication;

import com.inkblogdb.ddd.application.dto.authentication.AuthenticationDTO;
import com.inkblogdb.ddd.application.usecase.abort.UserNotFondException;
import com.inkblogdb.ddd.application.usecase.authentication.LoginUseCase;
import com.inkblogdb.ddd.domain.model.user.UserId;
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
  public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws UserNotFondException {
    AuthenticationDTO authenticationDTO = loginUseCase.login(
        new UserId(authenticationRequest.userId())
    );
    return new AuthenticationResponse(
        authenticationDTO.token(),
        authenticationDTO.playerId()
    );
  }

}
