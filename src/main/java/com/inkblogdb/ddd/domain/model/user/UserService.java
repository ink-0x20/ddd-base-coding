package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.DomainException;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private static final int MAX_RETRY = 10;

  public void addUser(UserId userId) {
    PlayerId playerId = Stream.generate(PlayerId::generate)
        .limit(MAX_RETRY)
        .filter(id -> userRepository.existsByPlayerId(id).isNotExists())
        .findFirst()
        .orElseThrow(() -> new DomainException("プレイヤーIDの生成に失敗"));
    userRepository.addUser(userId, playerId);
  }

}
