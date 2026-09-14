package com.inkblogdb.ddd.domain.model.authentication;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

  @InjectMocks
  private AuthenticationService authenticationService;
  @Mock
  private AuthenticationRepository authenticationRepository;

  @Test
  void ログインできること() {
    // given
    PlayerId playerId = PlayerId.generate();

    // when
    authenticationService.login(playerId);

    // then
    verify(authenticationRepository).login(any(), any());
  }

}
