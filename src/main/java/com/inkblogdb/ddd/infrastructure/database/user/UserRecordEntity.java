package com.inkblogdb.ddd.infrastructure.database.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRecordEntity {

  private String id;
  private String name;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
