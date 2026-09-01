package com.inkblogdb.ddd.infrastructure.database.user;

import org.apache.ibatis.annotations.Param;

import java.util.UUID;

public interface UserMapper {

  UserRecordEntity findById(@Param("id") UUID id);

  void save(UserRecordEntity userRecordEntity);

}
