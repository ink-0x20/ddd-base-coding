package com.inkblogdb.ddd.domain.model.user;

import java.util.Optional;

public interface UserRepository {

  Optional<User> findById(UserId userId);

}
