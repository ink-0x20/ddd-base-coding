package com.inkblogdb.ddd.infrastructure.database.user;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

  UserRecordEntity findById(@Param("id") String id);

}
