package com.inkblogdb.ddd.presentation.api.v1;

import com.inkblogdb.ddd.application.dto.user.UserDTO;
import com.inkblogdb.ddd.application.usecase.abort.UserNotFondException;
import com.inkblogdb.ddd.application.usecase.user.AddUserUseCase;
import com.inkblogdb.ddd.application.usecase.user.GetUserUseCase;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.user.UserName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final GetUserUseCase getUserUseCase;
  private final AddUserUseCase addUserUseCase;

  @GetMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public UserDTO getUser(@PathVariable String userId) throws UserNotFondException {
    return getUserUseCase.getUser(new UserId(userId));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponse addUser(@RequestBody UserRequest userRequest) {
    UserName userName = new UserName(userRequest.name());
    UserDTO userDTO = addUserUseCase.addUser(userName);
    return new UserResponse(userDTO.id(), userDTO.name());
  }

}
