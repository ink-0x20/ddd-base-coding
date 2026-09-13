package com.inkblogdb.ddd.domain.model.authentication;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final AuthenticationRepository authenticationRepository;

  public Authentication login(PlayerId playerId) {
    AuthenticationExpiration authenticationExpiration = AuthenticationExpiration.until4AMTheNextTime();
    AuthenticationToken authenticationToken = authenticationRepository.login(playerId, authenticationExpiration);
    return new Authentication(
        playerId,
        authenticationToken
    );
  }

}
