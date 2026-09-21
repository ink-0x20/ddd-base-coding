package com.inkblogdb.ddd.infrastructure.database.command.user;

import java.util.UUID;

public record UserRecordEntity(UUID id, String playerId) {

}
