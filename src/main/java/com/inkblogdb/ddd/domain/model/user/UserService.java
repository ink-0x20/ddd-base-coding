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

  public void addUser(UserId userId) {
    PlayerId playerId = Stream.generate(PlayerId::generate)
        .limit(10)
        .filter(userRepository::notExistsByPlayerId)
        .findFirst()
        .orElseThrow(() -> new DomainException("プレイヤーIDの生成に失敗"));
    userRepository.addUser(userId, playerId);
  }

}
