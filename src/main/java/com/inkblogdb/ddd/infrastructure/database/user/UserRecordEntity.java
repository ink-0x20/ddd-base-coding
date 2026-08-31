package com.inkblogdb.ddd.infrastructure.database.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRecordEntity {

  private String id;
  private String name;

}
