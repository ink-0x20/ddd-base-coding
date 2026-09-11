package com.inkblogdb.ddd.infrastructure.database.user;

public interface UserMapper {

  UserRecordEntity findById(String id);

  void save(UserRecordEntity userRecordEntity);

}
