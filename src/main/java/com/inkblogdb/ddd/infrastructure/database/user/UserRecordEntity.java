package com.inkblogdb.ddd.infrastructure.database.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRecordEntity {

  private UUID id;
  private String name;

}
