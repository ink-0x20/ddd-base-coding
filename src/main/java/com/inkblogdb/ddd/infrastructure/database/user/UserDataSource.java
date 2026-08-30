package com.inkblogdb.ddd.infrastructure.database.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserDataSource {

  private final UserMapper userMapper;

  public void findById() {
    userMapper.findById(UUID.randomUUID().toString());
  }

}
